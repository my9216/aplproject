package com.apl.traffic.cop.scan;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.tools.ant.taskdefs.condition.And;

import com.apl.traffic.cop.bean.BKGRangeBean;
import com.apl.traffic.cop.bean.common.BaseBean;
import com.apl.traffic.cop.exception.DataException;
import com.apl.traffic.cop.exception.UserException;
import com.apl.traffic.cop.form.QuartzFileForm;
import com.apl.traffic.cop.util.FileUtil;
import com.apl.traffic.cop.util.Logger;
import com.apl.traffic.cop.util.TrafficCopUtils;

public class QuartzFile {

	private static Logger logger = Logger.getLogger(QuartzFile.class);
	private ArrayList<String> wbpList = new ArrayList<String>();
	private ArrayList<String> q2cList = new ArrayList<String>();
	private String head;
	private String center;
	private String foot;

	// the number of wbp file;
	private int wbpFile = 0;
	// the number of q2c file;
	private int q2cFile = 0;
	// number of scan of sum file;
	private int sumFile = 0;

	/**
	 * 执行入口类
	 * 
	 */
	public void execute() {
		logger.info("Procedure start!");
		QuartzFileForm form = new QuartzFileForm(); // 创建配置信息对象
		File[] inputFiles = FileUtil.tolistFiles(form.getInputPath()); // 获取INPUT文件夹下所有文件
		if (inputFiles.length <= 0) {
			logger.info("folder is empty , finished!");
			return;
		} else {
			sumFile = inputFiles.length;
			for (File file : inputFiles) {
				String inputFile = file.toString();
				String backupFile = form.getBackupPath() + file.getName();
				// 备份文件
				if (!FileUtil.fileChannelCopy(inputFile, backupFile)) {
					logger.info("file: " + inputFile + " backup failure");
					// 备份失败，进行下一个文件读取;
					continue;
				}
				logger.info("File backup successfully!");
				String content = "";
				try {

					content = FileUtil.readFileByLine(inputFile); // 以行读取文件内容转换为文本
					resolveContent(content, form); // 将文本内容拆分为头、主体、尾

					logger.info("resolving!");
					concatContent(form); // 解析文本保存至arraylist
					getList(form, file.getName());// 输出拆分后文件
					// udpate last use id,one file once times;
					updateLastUseId(form.getNowave1UpdateMap(), "nonwave1");
					updateLastUseId(form.getWave1UpdateMap(), "wave1");
					
					//record file and new blnumber;
					recordFileAndBlNo(file.getName(), form.getBlNumbers());
					
				} catch (IOException e) {
					logger.error(file.getName() + " --- " + e.getMessage());
					// 日志写入数据库
					new UserException(form, file.getName(), "null#@#@#@#@#@" + e.getMessage());
				} catch (StringIndexOutOfBoundsException e) {
					logger.error(file.getName() + " --- " + e.getMessage());
					new UserException(form, file.getName(), "null#@#@#@#@#@" + e.getMessage());
				} catch (DataException e) {
					logger.error(file.getName() + " --- " + e.getMessage());
					new UserException(form, file.getName(), e.getMessage(), e.getNotifyLevel());
				} finally {
					// 清除上次文件内容
					wbpList.clear();
					q2cList.clear();
					form.getWave1UpdateMap().clear();
					form.getNowave1UpdateMap().clear();
					form.getBlNumbers().clear();
					FileUtil.delFile(inputFile); // 删除文件
					logger.info("Procedure end!");
				}
			}
			// 统计数据文件到数据库
			countFileNumber(sumFile, wbpFile, q2cFile);

			File[] backupfile = FileUtil.tolistFiles(form.getBackupPath());
			if (backupfile.length > 500) {// 打上时间戳压缩文件并清空文件夹
				FileUtil.compress(System.currentTimeMillis() + "backup.zip", form.getBackupPath());
				FileUtil.delallFile(backupfile);
			}
			File[] errorfile = FileUtil.tolistFiles(form.getErrorPath());
			if (errorfile.length > 500) {
				FileUtil.compress(System.currentTimeMillis() + "error.zip", form.getBackupPath());
				FileUtil.delallFile(errorfile);
			}
		}
	}

	/**
	 * 解析文本
	 * 
	 * @param content
	 * @param form
	 * @throws DataException
	 */
	public void concatContent(QuartzFileForm form) throws DataException {
		// 按行号如'02:拆分全部文本
		String[] subContent = center.split("(?<=[^?]|[?][?])'" + form.getBknRegex() + ":");
		for (int i = 1; i < subContent.length; i++) {
			concatBKG(subContent[i], form);// 拆分文本放到不同的arraylist中
		}
	}

	/**
	 * 若有blNo，则判断合法性，若无，则创建blNo; 按照wave1和非wave1放到不同的arraylist中,
	 * 
	 * @param iteraContent
	 * @param form
	 * @throws Exception
	 */
	private void concatBKG(String iteraContent, QuartzFileForm form) throws DataException {
		String result = "";
		String[] splitStr = iteraContent.split("(?<=[^?]|[?][?]):");// 将每个订单文本按照冒号拆分
		String bookingNo = splitStr[form.getBknIndex()];// 获取booking#
		String referenceNo = splitStr[0];
		String velvo = getvesselAndvoyage(splitStr);
		String refvel = referenceNo + "#@" + velvo;
		ArrayList<String> odList = getOdLocation(iteraContent, form);// 获取OD
		String isWave1 = checkWave(odList);// 通过OD判断是否为wave1
		// 增加 当blnumber 为9个0时，认为没有blnumber:
		if (bookingNo != null && bookingNo.indexOf("00000000") != -1) {
			bookingNo = "";
		}
		logger.info("通过OD判断为： " + isWave1);
		if ("".equals(bookingNo) || bookingNo == null) {// 判断booking#是否为空
			if ("wave1".equals(isWave1)) {
				bookingNo = "APLU" + form.createBKG(odList.get(0), getBPCode(head), "wave1", refvel);
				logger.info(bookingNo);
				splitStr[form.getBknIndex()] = bookingNo;
				result = stringConcat(splitStr, ":");
				form.getBlNumbers().add(bookingNo);
				wbpList.add(result);
				bookingNo = bookingNo.substring(4, bookingNo.length());
				splitStr[form.getBknIndex()] = bookingNo;
				result = stringConcat(splitStr, ":");
				q2cList.add(result);
			} else {
				bookingNo = "APLU" + form.createBKG(odList.get(0), getBPCode(head), "nonwave1", refvel);
				logger.info(bookingNo);
				splitStr[form.getBknIndex()] = bookingNo;
				result = stringConcat(splitStr, ":");
				form.getBlNumbers().add(bookingNo);
				wbpList.add(result);
			}
		} else {

			// check blNumber is legal
			if (!checkBlNoType(bookingNo)) {
				throw new DataException(getBPCode(head) + "#@" + refvel + "#@" + bookingNo + "#@"
						+ "the bookingNo is illegal, bookingNo: " + bookingNo, "1");
			}

			// get digit part of bookingNo
			String digitbookingNo = getDigtBlNo(bookingNo);
			digitbookingNo = digitbookingNo.substring(0, 9);

			// 判断当前报文文件是否为VIP
			// 为vip 则进入wbp 不做其他处理
			if (checkVip(head)) {
				wbpList.add(iteraContent);
				return;
			}
			// 非vip处理
			// OD匹配
			if (!checkBookingWaveR(digitbookingNo, form).equals(isWave1)) {
				throw new DataException(
						getBPCode(head) + "#@" + refvel + "#@" + bookingNo + "#@" + "booking# isn't match with OD",
						"1");
			}
			// BP范围匹配：若不再范围内，则抛出异常
			if (!checkRange(digitbookingNo, odList, isWave1)) {
				throw new DataException(getBPCode(head) + "#@" + refvel + "#@" + bookingNo + "#@" + "bookingNo:"
						+ bookingNo + " isn't in the range of current BP or origin", "1");
			}
			wbpList.add(iteraContent);
			if ("wave1".equals(isWave1)) {
				// 截取blno前面的APLU
				bookingNo = bookingNo.substring(4, bookingNo.length());
				splitStr[form.getBknIndex()] = bookingNo;
				result = stringConcat(splitStr, ":");
				q2cList.add(result);
			}
		}
	}

	/**
	 * 按wave和非wave保存到不同文件夹
	 * 
	 * @param form
	 */
	public void getList(QuartzFileForm form, String filename) {
		if (wbpList.size() != 0) {
			StringBuffer wbp = new StringBuffer();
			for (String temp : wbpList) {
				wbp.append("'02:".concat(temp));
			}
			if (q2cList.size() != 0 && q2cList.size() != wbpList.size()) {
				FileUtil.writeFile(head + wbp + foot, form.getWbpPath() + "wbp" + filename);
			} else {
				FileUtil.writeFile(head + wbp + foot, form.getWbpPath() + filename);
			}
			wbpFile++;
		}
		if (q2cList.size() != 0) {
			StringBuffer q2c = new StringBuffer();
			for (String temp : q2cList) {
				q2c.append("'02:".concat(temp));
			}
			if (wbpList.size() != 0 && wbpList.size() != q2cList.size()) {
				FileUtil.writeFile(head + q2c + foot, form.getQ2cPath() + "q2c" + filename);
			} else {
				FileUtil.writeFile(head + q2c + foot, form.getQ2cPath() + filename);
			}
			q2cFile++;
		}
	}

	/**
	 * 将内容拆成头、内容、底部
	 * 
	 * @param content
	 * @param form
	 * @return
	 */
	private void resolveContent(String content, QuartzFileForm form) {
		try {
			head = content.substring(0, content.indexOf(form.getHeadRegex()));

			center = " "
					+ content.substring(content.indexOf(form.getHeadRegex()), content.indexOf(form.getFootRegex()));
			foot = content.substring(content.indexOf(form.getFootRegex()));
		} catch (StringIndexOutOfBoundsException e) {
			logger.error("illegal message content!");
			throw new StringIndexOutOfBoundsException(
					"Exception: " + e.getMessage() + "   Cause: " + "illegal message content !");
		}
	}

	/**
	 * 获取订单的OD,首先获取配置文件中OD的位置,根据位置拆分文本获取O和D
	 * 
	 * @param temp
	 * @param form
	 * @return
	 */
	public ArrayList<String> getOdLocation(String temp, QuartzFileForm form) {
		ArrayList<String> odList = new ArrayList<String>();
		String[] ods = form.getOdLocation().split(";");
		try {
			for (String od : ods) {
				String odKey = od.split("-")[0];
				int odIndex = Integer.valueOf(od.split("-")[1]) - 2;
				String value = " " + temp.split("(?<=[^?]|[?][?])'" + odKey + ":")[1];
				value = value.split("(?<=[^?]|[?][?]):")[odIndex].trim();
				odList.add(value);
			}
		} catch (Exception e) {
			logger.error("illegal OD");
			throw new StringIndexOutOfBoundsException("Exception: " + e.getMessage() + "   Cause: " + "illegal OD!");
		}
		return odList;
	}

	/**
	 * 根据OD判断是否是wave1，只有当O和D都在wave1的区间内并且O不为US时，该航线为wave1
	 * 
	 * @param rulelist
	 * @return
	 * @throws DataException
	 */
	public String checkWave(ArrayList<String> odList) throws DataException {
		for (int i = 0; i < odList.size(); i++) {
			if ("".equals(odList.get(i)) && i == 0) {
				throw new DataException("Origin is null !", "1");
			}
			if ("".equals(odList.get(i)) && i == 1) {
				throw new DataException("Destination is null !", "1");
			}
		}
		// 判断destination：
		if ("US".equals(odList.get(1))) {
			return "nonwave1";
		}
		String sql = "select 'origin' as code from T_REF_LOCATION_WAVE1 where LOCATION like ? "
				+ "union select 'destination' as code from T_REF_LOCATION_WAVE1 where LOCATION like ?";
		String[] parm = new String[2];
		parm[0] = odList.get(0)+"%";
		parm[1] = odList.get(1)+"%";
		List result = null;
		result = BaseBean.Query(sql, parm);
		if (result.size() < 2) {
			return "nonwave1";
		}
		return "wave1";
	}

	/**
	 * 根据发送方代码判断是否为vip:head的第5个位置为发送方代码 VIP 的判断规则：t_booking_party中字段UTD1 为1
	 * 标识为VIP，为其他值，不为vip
	 * 
	 * @param head
	 * @return true:vip ; false: 非vip
	 * @throws DataException
	 */
	private boolean checkVip(String head) throws DataException {
		String bookingpartcode = getBPCode(head);
		if (!("".equals(bookingpartcode))) {
			// 查找数据库，判断是否为vip:
			String sql = "select id from WEBEDI.T_BOOKING_PARTY where UTD1 = '1' and booking_party_code = ?";
			String[] parm = new String[1];
			parm[0] = bookingpartcode;
			ArrayList<ArrayList<String>> query = BaseBean.Query(sql, parm);
			if (query.size() != 0) {
				return true;
			}
			return false;
		}
		return false;
	}

	/**
	 * if bookingNo in current orign or BP range
	 * 
	 * @param bookingNo
	 * @param odList
	 * @return
	 * @throws DataException
	 */
	private boolean checkRange(String bookingNo, ArrayList<String> odList, String iswave1) throws DataException {
		// get BP code
		String bpcode = getBPCode(head);
		// get origin code
		String origin = odList.get(0);
		String[] parm = new String[3];
		parm[0] = origin;
		parm[1] = bpcode;
		parm[2] = bpcode;
		if ("wave1".equals(iswave1)) {
			String sql = "select 'origin' as identify, wave1_min as originmin,wave1_max as originmax from T_TRAFFIC_BOOKING_RANGE t where t.code=? and t.parent_id in(select id from T_TRAFFIC_BOOKING_RANGE f where f.code = ? and f.bk_level='4')"
					+ "union select 'bp' as identify ,wave1_min as defaultmin,wave1_max as defaultmax from T_TRAFFIC_BOOKING_RANGE r where r.code=? and r.bk_level='4'";
			return matchRange(sql, parm, bookingNo);
		} else {
			String sql = "select 'origin' as identify, no_wave1_min as originmin,no_wave1_max as originmax from T_TRAFFIC_BOOKING_RANGE t where t.code=? and t.parent_id in(select id from T_TRAFFIC_BOOKING_RANGE f where f.code = ? and f.bk_level='4')"
					+ "union select 'bp' as identify ,no_wave1_min as defaultmin,no_wave1_max as defaultmax from T_TRAFFIC_BOOKING_RANGE r where r.code=? and r.bk_level='4'";
			return matchRange(sql, parm, bookingNo);
		}
	}

	// check if the blnumber in the result of range
	private boolean matchRange(String sql, String[] param, String blNo) {
		long blnumber = Long.valueOf(blNo); 
		ArrayList<ArrayList<String>> query = BaseBean.Query(sql, param);
		if (query.size() != 0) { 
			List<BKGRangeBean> originList = new ArrayList<BKGRangeBean>();
			List<BKGRangeBean> bpList = new ArrayList<BKGRangeBean>();
			for (ArrayList<String> arrayList : query) {
				BKGRangeBean bkg = new BKGRangeBean();
				bkg.setIdentify(arrayList.get(0));
				bkg.setBlnoMin(arrayList.get(1));
				bkg.setBlnoMax(arrayList.get(2));
				if (bkg.getIdentify().indexOf("origin") != -1) {
					originList.add(bkg);
				} else {
					bpList.add(bkg);
				}
			}
			if (originList.size() != 0) {
				return isRangeinList(originList, blnumber);
			} else {
				return isRangeinList(bpList, blnumber);
			}
		}
		return false;
	}

	private boolean isRangeinList(List<BKGRangeBean> list, long blnumber) {
		for (BKGRangeBean bean : list) {
			if ((Long.valueOf(bean.getBlnoMax()) >= blnumber) && (Long.valueOf(bean.getBlnoMin()) <= blnumber)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * get BP code ,after resolveContent
	 * 
	 * @param headSt
	 * @return
	 * @throws DataException
	 */
	private String getBPCode(String headSt) throws DataException {
		String bookingpartcode = "";
		if (headSt == null || "".equals(headSt)) {
			headSt = head;
		}

		String[] split = headSt.split(":");
		if (split.length < 5) {
			throw new DataException("null#@#@#@#@#@the head of message illegal ! value: " + headSt, "0");
		}
		bookingpartcode = split[4];
		if (bookingpartcode == null || "".equals(bookingpartcode)) {
			throw new DataException("null#@#@#@#@#@no BP code !", "0");
		}
		return bookingpartcode;
	}
	// get vessel and voyage 
	private String getvesselAndvoyage(String[] content) {
		String result = "";
		try {
			result = content[25] + "#@" + content[26];
		} catch (Exception e) {
			result = "#@";
		}
		return result;
	}

	/**
	 * check the blno is legal
	 * 
	 * @param blno
	 * @return
	 */
	private boolean checkBlNoType(String blno) {
		Pattern matches = Pattern.compile(TrafficCopUtils.blNoReg);
		Matcher matcher = matches.matcher(blno);
		boolean result = matcher.matches();
		return result;
	}

	/**
	 * get digt of blnumber
	 * 
	 * @param blnumber
	 * @return
	 */
	private String getDigtBlNo(String blnumber) {
		String reg = "^\\D*";
		Pattern matches = Pattern.compile(reg);
		Matcher matcher = matches.matcher(blnumber);
		String digtBLNo = matcher.replaceAll("");
		return digtBLNo;
	}

	/**
	 * 根据bookingNo判断是否为wave1: 数字部分：若以019开始，则为wave1:
	 * 
	 * @param bookingNo
	 * @param form
	 * @return
	 * @throws DataException
	 */
	public String checkBookingWave(String bookingNo, QuartzFileForm form) {
		String result = "nonwave1";
		if (bookingNo.indexOf("019") == 0) {
			result = "wave1";
		} else {
			result = "nonwave1";
		}
		return result;
	}
	public String checkBookingWaveR(String bookingNo, QuartzFileForm form) {
		String result = "nonwave1";
		Long blNo = Long.valueOf(bookingNo);
		Long wave1min = Long.valueOf(form.getWave1min());
		Long wave1max = Long.valueOf(form.getWave1max());
		if (wave1min<=blNo && blNo<=wave1max) {
			result = "wave1";
		}
		return result;
	}

	/**
	 * update last use id by type
	 * 
	 * @param map
	 * @param type:
	 *            wave1,nonwave1
	 * @throws DataException
	 */
	private void updateLastUseId(Map<String, String> map, String type) throws DataException {
		String sql = "";
		if ("wave1".equals(type)) {
			sql = "update T_TRAFFIC_BOOKING_RANGE set wave1_last_used_id = ? where id=?";
		} else {
			sql = "update T_TRAFFIC_BOOKING_RANGE set no_wave1_last_used_id = ? where id=?";
		}
		BaseBean.batchUpdate(map, sql);
	}
	
	private void recordFileAndBlNo(String fileName,List<String> blnumbers) throws DataException
	{
		Map<String, String> map = new HashMap<String, String>();
		for (String blnumber : blnumbers) {
			map.put(blnumber, fileName);
		}
		String sql = "insert into t_traffic_file_log(id,filename,blnumber,CREATE_TIME,type) values(SEQ_T_traffic_file_log.nextval,?,?,sysdate,'new')";
		BaseBean.batchUpdate(map, sql);
	}
	

	/**
	 * count file
	 * 
	 * @param totalFile
	 * @param wbpFile
	 * @param q2cFile
	 */
	private void countFileNumber(int totalFile, int wbpFile, int q2cFile) {
		if (totalFile == 0) {
			logger.info("there is no file!");
			return;
		}
		int errorFile = totalFile - wbpFile;
		String sql = "insert into t_traffic_convert_daily_log(id,log_date,file_quantity,err_file_quantity,wbp_file_quantity,q2c_file_quantity,modules)"
				+ "values(SEQ_T_DAILY_LOG.nextval,sysdate,?,?,?,?,?)";
		String[] param = new String[5];
		param[0] = totalFile + "";
		param[1] = errorFile + "";
		param[2] = wbpFile + "";
		param[3] = q2cFile + "";
		param[4] = "TRAFFIC";

		try {
			int update = BaseBean.Update(sql, param);
			if (update != 1) {
				logger.info("update table t_traffic_convert_daily_log fail");
			}
		} catch (Exception e) {
			logger.error("update table t_traffic_convert_daily_log fail :" + e.getMessage());
		}

	}

	public String stringConcat(String[] pending, String division) {
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < pending.length; i++) {
			result.append(pending[i].concat(division));
		}
		return result.toString();
	}

}

package com.traffic.bean;

import java.sql.SQLException;
import java.util.Vector;


import com.bean.common.BaseBean;

public class ManagerBean extends BaseBean{
	private static final String S_SQL_SEARCHBP = "SELECT t.ID,BK_LEVEL,CODE,JUDGE,PARENT_ID,NO_WAVE1_MIN,NO_WAVE1_MAX,NO_WAVE1_LAST_USED_ID,WAVE1_MIN,WAVE1_MAX,WAVE1_LAST_USED_ID,ISDELETE,DESC_CN,APL_OFFICE FROM T_TRAFFIC_BOOKING_RANGE t left join webedi.T_BOOKING_PARTY b on t.CODE=b.BOOKING_PARTY_CODE  where t.CODE= ? and ISDELETE is null ";
	private static final String S_SQL_SEARCHEDITORIGN = "SELECT ID,BK_LEVEL,CODE,JUDGE,PARENT_ID,NO_WAVE1_MIN,NO_WAVE1_MAX,NO_WAVE1_LAST_USED_ID,WAVE1_MIN,WAVE1_MAX,WAVE1_LAST_USED_ID,ISDELETE FROM T_TRAFFIC_BOOKING_RANGE t where t.CODE= ? and t.PARENT_ID =? and ISDELETE is null ";
	
	private static final String S_SQL_SEARCHLOCATION = "SELECT ID,BK_LEVEL,CODE,JUDGE,PARENT_ID,NO_WAVE1_MIN,NO_WAVE1_MAX,NO_WAVE1_LAST_USED_ID,WAVE1_MIN,WAVE1_MAX,WAVE1_LAST_USED_ID,ISDELETE FROM T_TRAFFIC_BOOKING_RANGE t where t.CODE= ? and t.bk_level='3' and ISDELETE is null ";
	
	private static final String S_SQL_SEARCHLIST = "SELECT a.ID,BK_LEVEL,CODE,JUDGE,PARENT_ID,NO_WAVE1_MIN,NO_WAVE1_MAX,NO_WAVE1_LAST_USED_ID,WAVE1_MIN,WAVE1_MAX,WAVE1_LAST_USED_ID,ISDELETE,DESC_CN,APL_OFFICE FROM T_TRAFFIC_BOOKING_RANGE a left join webedi.T_BOOKING_PARTY b on a.CODE=b.BOOKING_PARTY_CODE  where code!='default' and ISDELETE is null and BK_LEVEL=? and (lower(code) like ? or DESC_CN like ?) ORDER BY CODE ";
	private static final String S_SQL_SEARCHPARTENTLIST = "SELECT a.ID,BK_LEVEL,CODE,JUDGE,PARENT_ID,NO_WAVE1_MIN,NO_WAVE1_MAX,NO_WAVE1_LAST_USED_ID,WAVE1_MIN,WAVE1_MAX,WAVE1_LAST_USED_ID,ISDELETE,DESC_CN FROM T_TRAFFIC_BOOKING_RANGE a left join webedi.T_BOOKING_PARTY b on a.CODE=b.BOOKING_PARTY_CODE where PARENT_ID=?";
	
	private static final String S_SQL_SEARCHDEFAULTLIST = "SELECT a.ID,BK_LEVEL,CODE,JUDGE,PARENT_ID,NO_WAVE1_MIN,NO_WAVE1_MAX,NO_WAVE1_LAST_USED_ID,WAVE1_MIN,WAVE1_MAX,WAVE1_LAST_USED_ID,ISDELETE,DESC_CN FROM T_TRAFFIC_BOOKING_RANGE a left join webedi.T_BOOKING_PARTY b on a.CODE=b.BOOKING_PARTY_CODE where PARENT_ID=?";
	
	private static final String S_SQL_LOCATIN = "SELECT cast(' ' as nvarchar2(15))  comid, cast('ALL' as nvarchar2(15)) text from dual t  union SELECT DISTINCT t.APL_OFFICE as comid,t.APL_OFFICE as text from webedi.T_BOOKING_PARTY t where t.APL_OFFICE is not null";
	private static final String S_SQL_BPCODE = "SELECT t.BOOKING_PARTY_CODE as comid,t.DESC_CN as text from webedi.T_BOOKING_PARTY t where t.APL_OFFICE = ? ORDER BY DESC_CN";
	private static final String S_SQL_PARTENTCODE = "SELECT t.BOOKING_PARTY_CODE as comid,t.DESC_CN as text from T_TRAFFIC_BOOKING_RANGE a join webedi.T_BOOKING_PARTY t on a.code=t.BOOKING_PARTY_CODE";
	private static final String S_SQL_ORIGN = "select unloc_cd as comid,location as text from webedi.t_ref_location where iso_country_cd = 'CN' ORDER BY location";
	
	private static final String S_SQL_INSERT = "INSERT INTO T_TRAFFIC_BOOKING_RANGE (ID,BK_LEVEL,CODE,PARENT_ID,NO_WAVE1_MIN,NO_WAVE1_MAX,WAVE1_MIN,WAVE1_MAX,NO_WAVE1_LAST_USED_ID,WAVE1_LAST_USED_ID) VALUES (SEQ_T_TRAFFIC_BOOKING_RANGE.nextval,?,?,?,?,?,?,?,?,?)";
	
	private static final String S_SQL_INSERT_DEFAULT = "INSERT INTO T_TRAFFIC_BOOKING_RANGE (ID,BK_LEVEL,CODE,PARENT_ID,NO_WAVE1_MIN,NO_WAVE1_MAX,WAVE1_MIN,WAVE1_MAX,NO_WAVE1_LAST_USED_ID,WAVE1_LAST_USED_ID) SELECT SEQ_T_TRAFFIC_BOOKING_RANGE.nextval,?,'default',ID,?,?,?,?,?,? FROM T_TRAFFIC_BOOKING_RANGE WHERE code = ?";
	private static final String S_SQL_UPDATESTATE = "UPDATE T_TRAFFIC_BOOKING_RANGE set NO_WAVE1_MAX=NO_WAVE1_LAST_USED_ID,WAVE1_MAX=WAVE1_LAST_USED_ID,ISDELETE='1' where id=?";
	private static final String S_SQL_UPDATE = "UPDATE T_TRAFFIC_BOOKING_RANGE set NO_WAVE1_LAST_USED_ID=?,WAVE1_LAST_USED_ID=? where id=(select PARENT_ID from T_TRAFFIC_BOOKING_RANGE where id=?)";
	private static final String S_SQL_UPDATEDEFAULT = "UPDATE T_TRAFFIC_BOOKING_RANGE set NO_WAVE1_MIN=?,NO_WAVE1_MAX=?,WAVE1_MIN=?,WAVE1_MAX=?,NO_WAVE1_LAST_USED_ID=?,WAVE1_LAST_USED_ID=?  where PARENT_ID=? and code='default'";
	private static final String S_SQL_UPDATEPARTENDID = "UPDATE T_TRAFFIC_BOOKING_RANGE set PARENT_ID=(select max(id) from T_TRAFFIC_BOOKING_RANGE where code=? and ISDELETE is null) where PARENT_ID=?";
	private static final String S_SQL_REMOVE = "UPDATE T_TRAFFIC_BOOKING_RANGE set NO_WAVE1_MAX=NO_WAVE1_LAST_USED_ID,WAVE1_MAX=WAVE1_LAST_USED_ID,ISDELETE='1' where id in (%s)";
	
	private String locationcode;
	private String bpcode;
	private String code;
	private String parentid;
	private String parentcode;
	private String level;
	private String wavemax;
	private String wavemin;
	private String wavelast;
	private String nowavemax;
	private String nowavemin;
	private String nowavelast;
	private String type;
	
	public ManagerBean(){
		locationcode="";
		bpcode="";
		level="";
		wavemax="";
		wavemin="";
		nowavemax="";
		nowavemin="";
	}
	
	/**
	 * 返回location列表
	 * @param page
	 * @param rows
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public Vector<?> GetLoadlocation(int page, int rows) throws SQLException, Exception{
		Vector<?> vec = null; // 结果集
		String sql = S_SQL_SEARCHLOCATION;
		String[] parameter = null;	
		parameter = new String [1];
		parameter[0] = code;
		vec = BaseBean.extractJSONArray(sql, parameter, page, rows);
		return vec;
	}
	
	/**
	 * 返回树形列表的父节点
	 * @param page
	 * @param rows
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public Vector<?> GetLoadlist(int page, int rows) throws SQLException, Exception{
		Vector<?> vec = null; // 结果集
		String sql = S_SQL_SEARCHLIST;
		String[] parameter = null;
		parameter = new String [3];
		parameter[0] = level;
		parameter[1] = "%"+code.toLowerCase()+"%";
		parameter[2] = "%"+code.toLowerCase()+"%";
		vec = BaseBean.extractJSONArray(sql, parameter, page, rows);
		return vec;
	}
	
	/**
	 * 返回树形列表的子节点
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public Vector<?> GetLoadPartentlist() throws SQLException, Exception{
		Vector<?> vec = null; // 结果集
		String sql = S_SQL_SEARCHPARTENTLIST;
		String[] parameter = null;
		parameter = new String [1];
		parameter[0] = parentid;
		if("valid".equals(type)){
			sql += " and ISDELETE is null ";
		}
		sql += " ORDER BY CODE,ISDELETE DESC,a.ID DESC";
		vec = BaseBean.extractJSONArray(sql, parameter, 0, 0);
		return vec;
	}
	
	/**
	 * 根据父ID返回所属的子节点信息
	 * @param page
	 * @param rows
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public Vector<?> GetLoaddefaultlist(int page, int rows) throws SQLException, Exception{
		Vector<?> vec = null; // 结果集
		String sql = S_SQL_SEARCHDEFAULTLIST;
		String[] parameter = null;
		parameter = new String [1];
		parameter[0] = parentid;
		if("valid".equals(type)){
			sql += " and ISDELETE is null ";
		}
		sql += " ORDER BY CODE,ISDELETE DESC,ID DESC";
		vec = BaseBean.extractJSONArray(sql, parameter, page, rows);
		return vec;
	}
	
	/**
	 * 返回bussiness party列表
	 * @param page
	 * @param rows
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public Vector<?> GetLoadbp(int page, int rows) throws SQLException, Exception{
		Vector<?> vec = null; // 结果集
		String sql = S_SQL_SEARCHBP;
		String[] parameter = null;	
		parameter = new String [1];
		parameter[0] = code;
		vec = BaseBean.extractJSONArray(sql, parameter, page, rows);
		return vec;
	}
	
	/**
	 * 返回orign列表
	 * @param page
	 * @param rows
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public Vector<?> Geteditorign(int page, int rows) throws SQLException, Exception{
		Vector<?> vec = null; // 结果集
		String sql = S_SQL_SEARCHEDITORIGN;
		String[] parameter = null;	
		parameter = new String [2];
		parameter[0] = code;
		parameter[1] = parentid;
		vec = BaseBean.extractJSONArray(sql, parameter, page, rows);
		return vec;
	}

	/**
	 * 返回location下拉列表
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public Vector<?> GetLocationList() throws SQLException, Exception{
		Vector<?> vec = null; // 结果集
		String sql = S_SQL_LOCATIN;
		String[] parameter = null;	
		vec = BaseBean.extractComboArray(sql, parameter, "comid","text");
		return vec;
	}
	
	/**
	 * 返回bpcode下拉框数据
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public Vector<?> GetBpcodeList() throws SQLException, Exception{
		Vector<?> vec = null; // 结果集
		String sql = S_SQL_BPCODE;
		String[] parameter = null;	
		parameter = new String [1];
		parameter[0] = locationcode;
		if(locationcode==null || "".equals(locationcode)){
			sql += " OR 1=1 ";
		}
		vec = BaseBean.extractComboArray(sql, parameter, "comid","text");
		return vec;
	}
	
	/**
	 *返回 BOOKING PARTY下拉框数据
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public Vector<?> GetPartentcodeList() throws SQLException, Exception{
		Vector<?> vec = null; // 结果集
		String sql = S_SQL_PARTENTCODE;
		String[] parameter = null;
		vec = BaseBean.extractComboArray(sql, parameter, "comid","text");
		return vec;
	}
	
	/**
	 * 返回 ORIGN下拉框数据
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public Vector<?> GetOrignList() throws SQLException, Exception{
		Vector<?> vec = null; // 结果集
		String sql = S_SQL_ORIGN;
		String[] parameter = null;
		vec = BaseBean.extractComboArray(sql, parameter, "comid","text");
		return vec;
	}
	

	/**
	 * 添加location信息
	 * @param type
	 * @throws Exception
	 */
	public void addlocation(String type) throws Exception {
		if(!"default".equals(type)){//添加location信息
			String sql = S_SQL_INSERT;
			String[] parameter = null;	
			parameter = new String [9];
			parameter[0] = level;
			parameter[1] = code;
			parameter[2] = parentid;
			parameter[3] = nowavemin;
			parameter[4] = nowavemax;
			parameter[5] = wavemin;
			parameter[6] = wavemax;
			parameter[7] = nowavelast;
			parameter[8] = wavelast;
			BaseBean.Update(sql, parameter);
		}else{//添加location节点下default信息
			String sql = S_SQL_INSERT_DEFAULT;
			String[] parameter = null;	
			parameter = new String [8];
			parameter[0] = level;
			parameter[1] = nowavemin;
			parameter[2] = nowavemax;
			parameter[3] = wavemin;
			parameter[4] = wavemax;
			parameter[5] = nowavelast;
			parameter[6] = wavelast;
			parameter[7] = code;
			BaseBean.Update(sql, parameter);
		}
	}

	/**
	 * 修改location信息
	 * @param id
	 * @throws Exception
	 */
	public void updatelocation(String id) throws Exception {
		String sql = S_SQL_UPDATESTATE;//将location状态改为删除状态
		String[] parameter = null;	
		parameter = new String [1];
		parameter[0] = id;
		BaseBean.Update(sql, parameter);
		addlocation("");//添加新的location信息
		sql = S_SQL_UPDATEPARTENDID;//修改location下的子节点的paretendid
		parameter = new String [2];
		parameter[0] = id;
		parameter[1] = code;
		BaseBean.Update(sql, parameter);
	}

	/**
	 * 修改默认信息
	 * @param id
	 * @throws Exception
	 */
	public void updatedefault(String id) throws Exception {
		String sql = S_SQL_UPDATESTATE;//将default状态改为删除状态
		String[] parameter = null;	
		parameter = new String [1];
		parameter[0] = id;
		BaseBean.Update(sql, parameter);
		sql  = S_SQL_UPDATE;//将default的父节点信息
		parameter = new String [3];
		parameter[0] = nowavelast;
		parameter[1] = wavelast;
		parameter[2] = id;
		BaseBean.Update(sql, parameter);
		sql = S_SQL_INSERT;//添加default信息
		parameter = new String [9];
		parameter[0] = level;
		parameter[1] = code;
		parameter[2] = parentid;
		parameter[3] = nowavemin;
		parameter[4] = nowavemax;
		parameter[5] = wavemin;
		parameter[6] = wavemax;
		parameter[7] = nowavelast;
		parameter[8] = wavelast;
		BaseBean.Update(sql, parameter);
	}
	
	/**
	 * 添加bussiness party信息
	 * @param type
	 * @throws Exception
	 */
	public void addbp(String type) throws Exception {
		if(!"default".equals(type)){
			String sql = S_SQL_INSERT;//添加bussiness party信息
			String[] parameter = null;	
			parameter = new String [9];
			parameter[0] = level;
			parameter[1] = code;
			parameter[2] = parentid;
			parameter[3] = nowavemin;
			parameter[4] = nowavemax;
			parameter[5] = wavemin;
			parameter[6] = wavemax;
			parameter[7] = nowavelast;
			parameter[8] = wavelast;
			BaseBean.Update(sql, parameter);
		}else{
			String sql = S_SQL_INSERT_DEFAULT;//添加bussiness party下的default信息
			String[] parameter = null;	
			parameter = new String [8];
			parameter[0] = level;
			parameter[1] = nowavemin;
			parameter[2] = nowavemax;
			parameter[3] = wavemin;
			parameter[4] = wavemax;
			parameter[5] = nowavemin;
			parameter[6] = wavemin;
			parameter[7] = code;
			BaseBean.Update(sql, parameter);
		}
	}

	/**
	 * 修改bussiness party信息
	 * @param id
	 * @throws Exception
	 */
	public void updatebp(String id) throws Exception {
		String sql = S_SQL_UPDATESTATE;//将bussiness party状态改为删除状态
		String[] parameter = null;	
		parameter = new String [1];
		parameter[0] = id;
		BaseBean.Update(sql, parameter);
		addbp("");//添加bussiness party信息
		sql = S_SQL_UPDATEDEFAULT;//修改bussiness party下的default信息
		parameter = new String [7];
		parameter[0] = nowavemin;
		parameter[1] = nowavemax;
		parameter[2] = wavemin;
		parameter[3] = wavemax;
		parameter[4] = nowavelast;
		parameter[5] = wavelast;
		parameter[6] = id;
		BaseBean.Update(sql, parameter);
		sql = S_SQL_UPDATEPARTENDID;////修改bussiness party下的子节点的paretendid
		parameter = new String [2];
		parameter[0] = code;
		parameter[1] = id;
		BaseBean.Update(sql, parameter);
	}
	
	/**
	 * 添加orign信息
	 * @throws Exception
	 */
	public void addorign() throws Exception {
			String sql = S_SQL_INSERT;
			String[] parameter = null;	
			parameter = new String [9];
			parameter[0] = level;
			parameter[1] = code;
			parameter[2] = parentid;
			parameter[3] = nowavemin;
			parameter[4] = nowavemax;
			parameter[5] = wavemin;
			parameter[6] = wavemax;
			parameter[7] = nowavelast;
			parameter[8] = wavelast;
			BaseBean.Update(sql, parameter);
	}
	
	/**
	 * 修改orign信息
	 * @param id
	 * @throws Exception
	 */
	public void updateorign(String id) throws Exception {
		String sql = S_SQL_UPDATESTATE;
		String[] parameter = null;	
		parameter = new String [1];
		parameter[0] = id;
		BaseBean.Update(sql, parameter);
		addorign();
	}
	
	/**
	 * 移除信息
	 * @param id
	 * @throws Exception
	 */
	public void remove(String[] id) throws Exception {
		String sql = S_SQL_REMOVE;
		String[] parameter = null;	
		sql = String.format(sql, BaseBean.ArrayToString(id));
		sql += " or PARENT_ID in (%s)";
		sql = String.format(sql, BaseBean.ArrayToString(id));
		BaseBean.Update(sql, parameter);
	}
	
	public String getLocationcode() {
		return locationcode;
	}

	public void setLocationcode(String locationcode) {
		this.locationcode = locationcode;
	}

	public String getBpcode() {
		return bpcode;
	}

	public void setBpcode(String bpcode) {
		this.bpcode = bpcode;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getWavemax() {
		return wavemax;
	}

	public void setWavemax(String wavemax) {
		this.wavemax = wavemax;
	}

	public String getWavemin() {
		return wavemin;
	}

	public void setWavemin(String wavemin) {
		this.wavemin = wavemin;
	}

	public String getNowavemax() {
		return nowavemax;
	}

	public void setNowavemax(String nowavemax) {
		this.nowavemax = nowavemax;
	}

	public String getNowavemin() {
		return nowavemin;
	}

	public void setNowavemin(String nowavemin) {
		this.nowavemin = nowavemin;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	public String getParentcode() {
		return parentcode;
	}

	public void setParentcode(String parentcode) {
		this.parentcode = parentcode;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getWavelast() {
		return wavelast;
	}

	public void setWavelast(String wavelast) {
		this.wavelast = wavelast;
	}

	public String getNowavelast() {
		return nowavelast;
	}

	public void setNowavelast(String nowavelast) {
		this.nowavelast = nowavelast;
	}
}

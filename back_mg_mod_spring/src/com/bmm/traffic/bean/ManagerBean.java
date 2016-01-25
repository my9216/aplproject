package com.bmm.traffic.bean;

import java.sql.SQLException;
import java.util.Vector;

import com.bmm.bean.common.BaseBean;
import com.bmm.traffic.util.PublicTools;

public class ManagerBean extends BaseBean {

	/* ��������, ��ѯ�������νṹ�ڸ������ݵ�sql */
	private static final String S_SQL_SEARCHLIST = "select "
			+ "a.ID, BK_LEVEL, CODE, PARENT_ID, NO_WAVE1_MIN, NO_WAVE1_MAX,WAVE1_MIN, WAVE1_MAX, "
			+ "'' as NO_WAVE1_LAST_USED_ID, "
			+ "'' as WAVE1_LAST_USED_ID, "
			+ "ISDELETE,DESC_CN,APL_OFFICE "
			+ "from "
			+ "T_TRAFFIC_BOOKING_RANGE a left join webedi.T_BOOKING_PARTY b on a.CODE=b.BOOKING_PARTY_CODE "
			+ "where CODE !='default' and (ISDELETE is null or ISDELETE <> 1) and BK_LEVEL=? and (lower(code) like ? or DESC_CN like ?) "
			+ "order by CODE ";
	/* ��������, ��ѯ�������νṹ�ڶ������ݵ�sql */
	private static final String S_SQL_SEARCHCHILDLIST = "select "
			+ "a.ID, BK_LEVEL, CODE, PARENT_ID, NO_WAVE1_MIN, NO_WAVE1_MAX, "
			+ "WAVE1_MIN, WAVE1_MAX, ISDELETE, DESC_CN, "
			+ "case when bk_level=5 then NO_WAVE1_LAST_USED_ID else (select NO_WAVE1_LAST_USED_ID from T_TRAFFIC_BOOKING_RANGE "
			+ "where parent_id=a.id and bk_level=a.bk_level + 1 and CODE='default' and (ISDELETE<>1 or ISDELETE is null))end as NO_WAVE1_LAST_USED_ID, "
			+ "case when bk_level=5 then WAVE1_LAST_USED_ID else (select WAVE1_LAST_USED_ID from T_TRAFFIC_BOOKING_RANGE "
			+ "where parent_id=a.id and bk_level=a.bk_level + 1 and CODE='default' and (ISDELETE<>1 or ISDELETE is null))end as WAVE1_LAST_USED_ID "
			+ "from "
			+ "T_TRAFFIC_BOOKING_RANGE a left join webedi.T_BOOKING_PARTY b on a.CODE=b.BOOKING_PARTY_CODE "
			+ "where PARENT_ID=?";

	
	/* �༭ҳ����, ����Codeֵ��ѯ����BusinessPartner���ݵ�sql */
	private static final String S_SQL_SEARCHBP = "SELECT t.ID,BK_LEVEL,CODE,JUDGE,PARENT_ID,NO_WAVE1_MIN,NO_WAVE1_MAX,NO_WAVE1_LAST_USED_ID,WAVE1_MIN,WAVE1_MAX,WAVE1_LAST_USED_ID,ISDELETE,DESC_CN,APL_OFFICE FROM T_TRAFFIC_BOOKING_RANGE t left join webedi.T_BOOKING_PARTY b on t.CODE=b.BOOKING_PARTY_CODE  where t.CODE= ? and ISDELETE is null ";
	/* �༭ҳ����, ����Codeֵ��ParentId��ѯ����Origin���ݵ�sql */
	private static final String S_SQL_SEARCHORIGIN = "SELECT ID,BK_LEVEL,CODE,JUDGE,PARENT_ID,NO_WAVE1_MIN,NO_WAVE1_MAX,NO_WAVE1_LAST_USED_ID,WAVE1_MIN,WAVE1_MAX,WAVE1_LAST_USED_ID,ISDELETE FROM T_TRAFFIC_BOOKING_RANGE t where t.CODE= ? and t.PARENT_ID =? and ISDELETE is null ";
	/* �༭ҳ����, ����Codeֵ��ѯ����Location���ݵ�sql */
	private static final String S_SQL_SEARCHLOCATION = "SELECT ID,BK_LEVEL,CODE,JUDGE,PARENT_ID,NO_WAVE1_MIN,NO_WAVE1_MAX,NO_WAVE1_LAST_USED_ID,WAVE1_MIN,WAVE1_MAX,WAVE1_LAST_USED_ID,ISDELETE FROM T_TRAFFIC_BOOKING_RANGE t where t.CODE= ? and t.bk_level='3' and ISDELETE is null ";
	/* �༭ҳ����, ��ѯ����Location��id��name��sql */
	private static final String S_SQL_LOCATION = "SELECT cast(' ' as nvarchar2(15))  comid, cast('ALL' as nvarchar2(15)) text from dual t  union SELECT DISTINCT t.APL_OFFICE as comid,t.APL_OFFICE as text from webedi.T_BOOKING_PARTY t where t.APL_OFFICE is not null";
	/* �༭ҳ����, ��ѯ����BusinessPartner��id��name��sql */
	private static final String S_SQL_BPCODE = "SELECT t.BOOKING_PARTY_CODE as comid,t.DESC_CN as text from webedi.T_BOOKING_PARTY t where t.APL_OFFICE = ? ORDER BY DESC_CN";
	/* �༭ҳ����, ��ѯ����Origin��id��name��sql */
	private static final String S_SQL_ORIGIN = "select unloc_cd as comid,location as text from webedi.t_ref_location where iso_country_cd = 'CN' ORDER BY location";

	/* ��T_TRAFFIC_BOOKING_RANGE����һ���µ����ݵ�sql */
	private static final String S_SQL_INSERT = "INSERT INTO T_TRAFFIC_BOOKING_RANGE (ID,BK_LEVEL,CODE,PARENT_ID,NO_WAVE1_MIN,NO_WAVE1_MAX,WAVE1_MIN,WAVE1_MAX,NO_WAVE1_LAST_USED_ID,WAVE1_LAST_USED_ID) VALUES (SEQ_T_TRAFFIC_BOOKING_RANGE.nextval,?,?,?,?,?,?,?,?,?)";
	/* ׷��һ��Default���ݵ�sql */
	private static final String S_SQL_INSERT_DEFAULT = "INSERT INTO T_TRAFFIC_BOOKING_RANGE (ID,BK_LEVEL,CODE,PARENT_ID,NO_WAVE1_MIN,NO_WAVE1_MAX,WAVE1_MIN,WAVE1_MAX,NO_WAVE1_LAST_USED_ID,WAVE1_LAST_USED_ID) SELECT SEQ_T_TRAFFIC_BOOKING_RANGE.nextval,?,'default',ID,?,?,?,?,?,? FROM T_TRAFFIC_BOOKING_RANGE WHERE code = ?";
	/*  */
	private static final String S_SQL_UPDATE = "UPDATE T_TRAFFIC_BOOKING_RANGE set NO_WAVE1_LAST_USED_ID=?,WAVE1_LAST_USED_ID=? where id=(select PARENT_ID from T_TRAFFIC_BOOKING_RANGE where id=?)";
	/* ���ɵ����ݵ�ISDELETE��Ϊ1, ����ɵ���־ */
	private static final String S_SQL_UPDATESTATUS = "UPDATE T_TRAFFIC_BOOKING_RANGE set NO_WAVE1_MAX=NO_WAVE1_LAST_USED_ID,WAVE1_MAX=WAVE1_LAST_USED_ID,ISDELETE='1' where id=?";
	/* �޸�Default��ֵ��sql */
	private static final String S_SQL_UPDATEDEFAULT = "UPDATE T_TRAFFIC_BOOKING_RANGE set NO_WAVE1_MIN=?,NO_WAVE1_MAX=?,WAVE1_MIN=?,WAVE1_MAX=?,NO_WAVE1_LAST_USED_ID=?,WAVE1_LAST_USED_ID=?  where PARENT_ID=? and code='default'";
	/* �޸������ӽڵ��ParentIdֵ */
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

	public ManagerBean() {
		locationcode = "";
		bpcode = "";
		level = "";
		wavemax = "";
		wavemin = "";
		nowavemax = "";
		nowavemin = "";
	}

	/**
	 * ����Codeֵ���ض�Ӧ��Location����
	 * 
	 * @param page
	 * @param rows
	 * @return vec �����
	 * @throws SQLException
	 * @throws Exception
	 */
	public Vector<?> getLoadlocation(int page, int rows) throws SQLException, Exception {
		// �����
		Vector<?> vec = null;
		String sql = S_SQL_SEARCHLOCATION;
		String[] parameter = null;
		parameter = new String[1];
		parameter[0] = code;
		vec = extractJSONArray(sql, parameter, page, rows);
		return vec;
	}

	/**
	 * ��ѯ���и�����Ϣ
	 * 
	 * @param page
	 * @param rows
	 * @return vec �����
	 * @throws SQLException
	 * @throws Exception
	 */
	public Vector<?> getLoadList(int page, int rows) throws SQLException, Exception {
		Vector<?> vec = null; // �����
		String sql = S_SQL_SEARCHLIST;
		String[] parameter = null;
		parameter = new String[3];
		parameter[0] = level;
		// ����Codeֵ���Դ�Сд
		parameter[1] = "%" + code.toLowerCase() + "%";
		parameter[2] = "%" + code.toLowerCase() + "%";
		vec = extractJSONArray(sql, parameter, page, rows);
		return vec;
	}

	/**
	 * ��ѯ�����Ӽ���Ϣ
	 * 
	 * @return vec �����
	 * @throws SQLException
	 * @throws Exception
	 */
	public Vector<?> getLoadChildList(int page, int rows) throws SQLException, Exception {
		Vector<?> vec = null; // �����
		String sql = S_SQL_SEARCHCHILDLIST;
		String[] parameter = null;
		parameter = new String[1];
		parameter[0] = parentid;
		// �ж��Ƿ���ɸѡ,����ж�Ӧ�Ĳ�ѯ����,�򽫽���ѯ����ƴ��SQL����WHERE������
		if ("Active".equals(type)) {
			sql += " and ISDELETE is null ";
		}
		sql += " ORDER BY CODE,ISDELETE DESC,a.ID DESC";
		vec = extractJSONArray(sql, parameter, page, rows);
		return vec;
	}

	/**
	 * ����ParentId�����������ӽڵ���Ϣ
	 * 
	 * @param page
	 * @param rows
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 * 
	 *             public Vector<?> getLoadDefaultList(int page, int rows)
	 *             throws SQLException, Exception { // ����� Vector<?> vec = null;
	 *             String sql = S_SQL_SEARCHCHILDLIST; String[] parameter =
	 *             null; parameter = new String[1]; parameter[0] = parentid; if
	 *             ("valid".equals(type)) { sql += " and ISDELETE is null "; }
	 *             sql += " ORDER BY CODE,ISDELETE DESC,ID DESC"; vec =
	 *             extractJSONArray(sql, parameter, page, rows); return vec; }
	 */

	/**
	 * ����Codeֵ��ѯBp��ͬ������Default
	 * 
	 * @param page
	 * @param rows
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public Vector<?> loadBusinessPartner(int page, int rows) throws SQLException, Exception {
		// �����
		Vector<?> vec = null;
		String sql = S_SQL_SEARCHBP;
		String[] parameter = null;
		parameter = new String[1];
		parameter[0] = code;
		vec = extractJSONArray(sql, parameter, page, rows);
		return vec;
	}

	/**
	 * ����Codeֵ��ParentId��ѯOrigin��ͬ������Default
	 * 
	 * @param page
	 * @param rows
	 * @return vec �����
	 * @throws SQLException
	 * @throws Exception
	 */
	public Vector<?> loadOrigin(int page, int rows) throws SQLException, Exception {
		// �����
		Vector<?> vec = null;
		String sql = S_SQL_SEARCHORIGIN;
		String[] parameter = null;
		parameter = new String[2];
		parameter[0] = code;
		parameter[1] = parentid;
		vec = extractJSONArray(sql, parameter, page, rows);
		return vec;
	}

	/**
	 * ��ѯLocation�����б����������
	 * 
	 * @return vec ��ѯ�����
	 * @throws SQLException
	 * @throws Exception
	 */
	public Vector<?> getLocationCombo() throws SQLException, Exception {
		// �����
		Vector<?> vec = null;
		String sql = S_SQL_LOCATION;
		String[] parameter = null;
		vec = extractComboArray(sql, parameter, "comid", "text");
		return vec;
	}

	/**
	 * ��ѯBp�����б����������
	 * 
	 * @return vec ��ѯ�����
	 * @throws SQLException
	 * @throws Exception
	 */
	public Vector<?> getBpCombo() throws SQLException, Exception {
		// �����
		Vector<?> vec = null; 
		String sql = S_SQL_BPCODE;
		String[] parameter = null;
		parameter = new String[1];
		parameter[0] = locationcode;
		if (locationcode == null || "".equals(locationcode)) {
			sql += " OR 1=1 ";
		}
		vec = extractComboArray(sql, parameter, "comid", "text");
		return vec;
	}

	/**
	 * ��ѯOrigin�����б����������
	 * 
	 * @return vec ��ѯ�����
	 * @throws SQLException
	 * @throws Exception
	 */
	public Vector<?> getOriginCombo() throws SQLException, Exception {
		// �����
		Vector<?> vec = null;
		String sql = S_SQL_ORIGIN;
		String[] parameter = null;
		vec = extractComboArray(sql, parameter, "comid", "text");
		return vec;
	}

	/**
	 * ����Location��Ϣ ��������һ��Location��, Ҫ����һ��BL#��Χ����һ����Defaultֵ
	 * 
	 * @param type	����, ͨ������ֵ�����ֱ���������Location����������Location��Ӧ��Defaultֵ
	 * @throws Exception
	 */
	public void addLocation(String type) throws Exception {
		if (!"default".equals(type)) {
			// ���location��Ϣ
			String sql = S_SQL_INSERT;
			String[] parameter = null;
			parameter = new String[9];
			parameter[0] = level;
			parameter[1] = code;
			parameter[2] = parentid;
			parameter[3] = nowavemin;
			parameter[4] = nowavemax;
			parameter[5] = wavemin;
			parameter[6] = wavemax;
			parameter[7] = nowavelast;
			parameter[8] = wavelast;
			update(sql, parameter);
		} else {
			// ���location�ڵ���default��Ϣ
			String sql = S_SQL_INSERT_DEFAULT;
			String[] parameter = null;
			parameter = new String[8];
			parameter[0] = level;
			parameter[1] = nowavemin;
			parameter[2] = nowavemax;
			parameter[3] = wavemin;
			parameter[4] = wavemax;
			parameter[5] = nowavelast;
			parameter[6] = wavelast;
			parameter[7] = code;
			update(sql, parameter);
		}
	}

	/**
	 * �޸�Location��Ϣ, ��ֱ�����޸Ĳ���, ���ǽ�ԭ���ļ�¼����, 
	 * 	ͬʱ���޸ĺ�����ݴ���һ���µļ�¼, ����ԭ�����е��Ӽ���ParentIdȫ��ָ���µ�Location
	 * 
	 * @param id  ��Ӧ��id����
	 * @throws Exception
	 */
	public void updateLocation(String id) throws Exception {
		// �Ƚ�location״̬��Ϊɾ��״̬
		String sql = S_SQL_UPDATESTATUS;
		String[] parameter = null;
		parameter = new String[1];
		parameter[0] = id;
		update(sql, parameter);
		// ����µ�Location��Ϣ
		addLocation("");
		// �޸�location�µ��ӽڵ��parentId
		sql = S_SQL_UPDATEPARTENDID;
		parameter = new String[2];
		parameter[0] = id;
		parameter[1] = code;
		update(sql, parameter);
	}

	/**
	 * �޸�Default��Ϣ, ��ֱ�����޸Ĳ���, ���ǽ�ԭ���ļ�¼����, ��ISDELETE��Ϊ1, ��lastUseId��ΪMaxId
	 * 	ͬʱ���޸ĺ�����ݴ���һ���µļ�¼
	 * 
	 * @param id	��Ӧ��id����
	 * @throws Exception
	 */
	public void updateDefault(String id) throws Exception {
		// ��default״̬��Ϊɾ��״̬
		String sql = S_SQL_UPDATESTATUS;
		String[] parameter = null;
		parameter = new String[1];
		parameter[0] = id;
		update(sql, parameter);
		// ��default�ĸ��ڵ���Ϣ
//		sql = S_SQL_UPDATE;
//		parameter = new String[3];
//		parameter[0] = nowavelast;
//		parameter[1] = wavelast;
//		parameter[2] = id;
//		update(sql, parameter);
		// ���default��Ϣ
		sql = S_SQL_INSERT;
		parameter = new String[9];
		parameter[0] = level;
		parameter[1] = code;
		parameter[2] = parentid;
		parameter[3] = nowavemin;
		parameter[4] = nowavemax;
		parameter[5] = wavemin;
		parameter[6] = wavemax;
		parameter[7] = nowavelast;
		parameter[8] = wavelast;
		update(sql, parameter);
	}

	/**
	 * ���bussiness party��Ϣ
	 * 
	 * @param type
	 * @throws Exception
	 */
	public void addbp(String type) throws Exception {
		if (!"default".equals(type)) {
			String sql = S_SQL_INSERT;// ���bussiness party��Ϣ
			String[] parameter = null;
			parameter = new String[9];
			parameter[0] = level;
			parameter[1] = code;
			parameter[2] = parentid;
			parameter[3] = nowavemin;
			parameter[4] = nowavemax;
			parameter[5] = wavemin;
			parameter[6] = wavemax;
			parameter[7] = nowavelast;
			parameter[8] = wavelast;
			update(sql, parameter);
		} else {
			String sql = S_SQL_INSERT_DEFAULT;// ���bussiness party�µ�default��Ϣ
			String[] parameter = null;
			parameter = new String[8];
			parameter[0] = level;
			parameter[1] = nowavemin;
			parameter[2] = nowavemax;
			parameter[3] = wavemin;
			parameter[4] = wavemax;
			parameter[5] = nowavemin;
			parameter[6] = wavemin;
			parameter[7] = code;
			update(sql, parameter);
		}
	}

	/**
	 * �޸�bussiness party��Ϣ
	 * 
	 * @param id
	 * @throws Exception
	 */
	public void updatebp(String id) throws Exception {
		// ��bussiness party״̬��Ϊɾ��״̬
		String sql = S_SQL_UPDATESTATUS;
		String[] parameter = null;
		parameter = new String[1];
		parameter[0] = id;
		update(sql, parameter);
		// ���bussiness party��Ϣ
		addbp("");
		// �޸�bussiness party�µ�default��Ϣ
		sql = S_SQL_UPDATEDEFAULT;
		parameter = new String[7];
		parameter[0] = nowavemin;
		parameter[1] = nowavemax;
		parameter[2] = wavemin;
		parameter[3] = wavemax;
		parameter[4] = nowavelast;
		parameter[5] = wavelast;
		parameter[6] = id;
		update(sql, parameter);
		// �޸�bussiness party�µ��ӽڵ��paretendid
		sql = S_SQL_UPDATEPARTENDID;
		parameter = new String[2];
		parameter[0] = code;
		parameter[1] = id;
		update(sql, parameter);
	}

	/**
	 * ���origin��Ϣ
	 * 
	 * @throws Exception
	 */
	public void addorign() throws Exception {
		String sql = S_SQL_INSERT;
		String[] parameter = null;
		parameter = new String[9];
		parameter[0] = level;
		parameter[1] = code;
		parameter[2] = parentid;
		parameter[3] = nowavemin;
		parameter[4] = nowavemax;
		parameter[5] = wavemin;
		parameter[6] = wavemax;
		parameter[7] = nowavelast;
		parameter[8] = wavelast;
		update(sql, parameter);
	}

	/**
	 * �޸�orign��Ϣ
	 * 
	 * @param id
	 * @throws Exception
	 */
	public void updateorign(String id) throws Exception {
		String sql = S_SQL_UPDATESTATUS;
		String[] parameter = null;
		parameter = new String[1];
		parameter[0] = id;
		update(sql, parameter);
		addorign();
	}

	/**
	 * �Ƴ���Ϣ
	 * 
	 * @param id
	 * @throws Exception
	 */
	public void remove(String[] id) throws Exception {
		String sql = S_SQL_REMOVE;
		String[] parameter = null;
		sql = String.format(sql, PublicTools.ArrayToString(id));
		sql += " or PARENT_ID in (%s)";
		sql = String.format(sql, PublicTools.ArrayToString(id));
		update(sql, parameter);
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

package com.apl.sipg.form;

import java.util.Properties;

import com.apl.sipg.util.PropertiesUtil;


/*
 * 保存配置参数
 */
public class Form {
	private Properties folderProp;
	private String inputPath;
	private String backupPath;
	private String errorPath;
	private int time;
	
	public Form(){
		folderProp = PropertiesUtil.getProperties("folder.properties");
		inputPath = folderProp.getProperty("input.path");
		backupPath = folderProp.getProperty("backup.path");
		errorPath = folderProp.getProperty("error.path");
		time = Integer.valueOf(folderProp.getProperty("time"));
	}

	
	public Properties getFolderProp() {
		return folderProp;
	}

	public void setFolderProp(Properties folderProp) {
		this.folderProp = folderProp;
	}

	public String getInputPath() {
		return inputPath;
	}

	public void setInputPath(String inputPath) {
		this.inputPath = inputPath;
	}

	public String getBackupPath() {
		return backupPath;
	}

	public void setBackupPath(String backupPath) {
		this.backupPath = backupPath;
	}

	public String getErrorPath() {
		return errorPath;
	}

	public void setErrorPath(String errorPath) {
		this.errorPath = errorPath;
	}


	public int getTime() {
		return time;
	}


	public void setTime(int time) {
		this.time = time;
	}
	
}

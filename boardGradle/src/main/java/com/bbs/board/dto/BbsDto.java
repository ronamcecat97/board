package com.bbs.board.dto;

public class BbsDto {
	private int seq;
	private String title;
	private String user;
	private String detl;
	private String fileUuid;
	private String fileName;
	private long maxCnt;
	private long ppc = 3;
	private long page;	
	private long startPage;		
	
	
	public long getMaxCnt() {
		return maxCnt;
	}
	public void setMaxCnt(long maxCnt) {
		this.maxCnt = maxCnt;
	}
	public long getPpc() {
		return ppc;
	}
	public void setPpc(long ppc) {
		this.ppc = ppc;
	}
	public long getPage() {
		return page;
	}
	public void setPage(long page) {
		this.page = page;
	}
	public long getStartPage() {
		startPage = (this.page - 1) * this.ppc;

		return startPage;
	}
	
	
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getDetl() {
		return detl;
	}
	public void setDetl(String detl) {
		this.detl = detl;
	}
	public String getFileUuid() {
		return fileUuid;
	}
	public void setFileUuid(String fileUuid) {
		this.fileUuid = fileUuid;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


}

package es.uc3.tiw.web;

public class InfoMessage {

	private String msg;
	private TipoInfoMessage tipoMsg;
	private String cssClass;
	
	public InfoMessage(String msg, TipoInfoMessage tipoMsg, String cssClass){
		this.setMsg(msg);
		this.setTipoMsg(tipoMsg);
		this.setCssClass(cssClass);
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getCssClass() {
		return cssClass;
	}

	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}

	public TipoInfoMessage getTipoMsg() {
		return tipoMsg;
	}

	public void setTipoMsg(TipoInfoMessage tipoMsg) {
		this.tipoMsg = tipoMsg;
	}
	
}

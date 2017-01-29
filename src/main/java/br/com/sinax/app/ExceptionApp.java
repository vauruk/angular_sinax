package br.com.sinax.app;


/**
 * Excecao base para o framework PK. Todas as mensagens de excecao devem ser
 * internacionalizadas, ou seja: Devem ser criadas exceoes com codigos apenas, nunca
 * Strings literais.
 * 
 * @author vanderson
 */
public class ExceptionApp extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3376609644786305065L;
	/**
	 * Chave da excecao.
	 */
	private String key;
	/**
	 * modulo da excecao.
	 */
	private final String module = "web";
	/**
	 * Uma String de detalhe da excecao.
	 */
	private String detail = "";

	/**
	 * Array Default vazio.
	 */
	private static final Object[] EMPTY = new Object[] {};
	/**
	 * Parametros para mensagem.
	 */
	private Object[] params = EMPTY;
	/**
	 * Uma causa inicial para esta excecaoo. Por exemplo, uma NullPointerException
	 */
	private Throwable cause;
	
	public ExceptionApp(String key) {
		this.key = key;
	}

	/**
	 * Inicializa a excecao com uma chave e uma causa.
	 * 
	 * @param key Chave da excecao            
	 * @param cause Causa da excecao
	 */
	public ExceptionApp(String key, Throwable cause) {
		super(key,cause);
		this.key = key;
		this.cause = cause;
	}

	/**
	 * 
	 * Inicializa a excecaoo com uma chave e uma causa e parametros para substituicaoo.
	 * @param key Chave da excecaoo            
	 * @param cause Causa da excecaoo
	 * @param objects Parametros a serem substituidos
	 */
	public ExceptionApp(String key, Throwable cause, Object[] objects) {
		this(key, cause);
		this.params = objects.clone();
	}

	/**
	 * Inicializa a excecaoo com uma chave e parametros para substituicaoo.
	 * @param key Chave da excecaoo            
	 * @param objects Parametros a serem substituidos
	 */
	public ExceptionApp(String key, Object[] objects) {
		super(key);
		this.params = objects.clone();
	}

	/**
	 * Retorna uma String com a causa inicial cause, ou vazio caso nao exista uma.
	 * 
	 * @return string de causa
	 */
	public String getParentCause( ) {
		if (cause == null) {
			return "";
		}
		return " - " + cause.getClass().getName() 
				+ (cause.getMessage() !=null ? " " + cause.getMessage() :"");
	}
	/**
	 * @return key
	 */
	public String getKey( ) {
		return key;
	}

	/**
	 * @param key
	 *            set key
	 */
	public void setKey(String key) {
	}

	/**
	 * @return module
	 */
	public String getModule( ) {
		return module;
	}

	/**
	 * @param module
	 *            set module
	 */
	public void setModule(String module) {
	}

	/**
	 * @return params
	 */
	public Object[] getParams( ) {
		return params;
	}

	/**
	 * @param params
	 *            set params
	 */
	public void setParams(Object[] params) {
		this.params = params.clone();
	}

	/**
	 * @param detail
	 *            set detail
	 */
	public void setDetail(String detail) {
	}

	/**
	 * @return detail
	 */
	public String getDetail( ) {
		return detail;
	}
	

}

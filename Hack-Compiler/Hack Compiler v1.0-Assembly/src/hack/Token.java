/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hack;

public class Token {
    private String name;
    private String info;
    private int lineNo;
	public Token(String tokenName,String tokenInfo,int tokenLine){
		this.name=tokenName;
		this.info=tokenInfo;
                this.lineNo=tokenLine;
	}
	public String getName() {
		return name;
	}

	public String getInfo() {
		return info;
	}
        public int getLineNo() {
		return lineNo;
	}

}

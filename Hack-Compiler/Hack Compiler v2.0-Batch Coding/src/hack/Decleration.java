/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hack;

/**
 *
 * @author Noras Salman
 */
public class Decleration {

public String id_val;
public String type;
public Decleration(String id_val,String type){
		this.id_val=id_val;
                this.type=type;
	}
        public void setVal(String id_val) {
		this.id_val=id_val;
	}
        public void setType(String type) {
		this.type=type;
	}
	public String getVal() {
		return id_val;
	}
        public String getType() {
		return type;
	}



}

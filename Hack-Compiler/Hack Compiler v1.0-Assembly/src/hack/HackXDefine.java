/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hack;

import java.util.Vector;
import java.util.regex.Pattern;
/**
 *
 * @author Noras Salman
 */
public class HackXDefine {static String  letter, id, digit, num;
static String  FOR,to,integer,string,HackIn, HackOut, IF, ELSE, Hack,not;
	static Vector<String> takenWord = new Vector<String>();
        static  {
                digit = "\\d";
		num = "[\\d]+";
		letter = "[a-zA-Z]";
		id = "["+letter+"]["+letter+" "+digit+"]*";
                FOR= "[f][o][r]";
                to="[t][o]";
                integer = "[i][n][t][e][g][e][r]";
		string = "[s][t][r][i][n][g]";
                HackIn = "[h][a][c][k][I][n]";
		HackOut = "[h][a][c][k][O][u][t]";
                not= "[n][o][t]";
                IF = "[i][f]";
		ELSE = "[e][l][s][e]";
		Hack = "[h][a][c][k]";
		takenWord.add(FOR);
                takenWord.add(to);
                takenWord.add(integer);
                takenWord.add(string);
		takenWord.add(HackIn);
		takenWord.add(HackOut);
		takenWord.add(IF);
		takenWord.add(ELSE);
                takenWord.add(Hack);
                takenWord.add(not);

	}

	public static boolean isAKeyWord(String s) {
		boolean kw;
		for (int i = 0; i <takenWord.size(); i++) {
			if (kw = Pattern.matches(takenWord.get(i), s)) {
                            return kw;
			}
		}
		return false;
	}


	public static boolean isAnID(String s){
		boolean idd;
		idd=Pattern.matches(id, s);
		return idd;
	}

	public static boolean isAnumb(String s){
		boolean nu;
		nu=Pattern.matches(num, s);

		return nu;

	}

}


package hack;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.Vector;

public class Lexical {

public static int openclose=0;// Every open "(" must have a close ")"
public static Vector<Token>  tokens ;
public static boolean LexicalDone;
  public static void LexicalAnalysis() throws FileNotFoundException, IOException{
       LexicalDone=true;
     tokens = new Vector<Token>();
      File f = new File("src\\hack\\InputedCode.txt");
      int n,m;
        if (f.exists()) {
			BufferedReader br = new BufferedReader(new FileReader(f));
                        StreamTokenizer st = new StreamTokenizer(br);
                        st.eolIsSignificant(true);//for end of lin
                        st.slashStarComments(true);
                        st.ordinaryChar('\u002F');// /
                        st.ordinaryChar('\u002D');// -
                        st.ordinaryChar('\u002B');// +
                        n=st.nextToken();
                        while ( n!= StreamTokenizer.TT_EOF){
                        m=st.ttype;
                        switch(m){
                            case -2:{
                        int num=(int)st.nval;// cuz it is a double
                        String s=num+"";//cuz regex make them string
                        if (HackXDefine.isAnumb(s)){
                            tokens.add(new Token("num",s,st.lineno() ));
                              
                         /*   if (st.nextToken()==StreamTokenizer .TT_WORD){
                            if ((HackXDefine.isAnID(st.sval)))
                                 System.out.println("Lexical Error: Invalid id name at line "+st.lineno());

                               tokens.removeElementAt(tokens.size()-1);
                        }

                            else st.pushBack();*/
                          
                        }//if (isAnumb(s))

                          }break;//case -2
                            case -3:{//Eather Key word or ID
                          if (HackXDefine.isAKeyWord(st.sval)) {//case the word is a Key Word
                          if (st.sval.compareTo("integer") == 0) {
				tokens.add(new Token("integer", null,st.lineno()));
                         }else if (st.sval.compareTo("string") == 0) {
				tokens.add(new Token("string", null,st.lineno()));
                         } else if (st.sval.compareTo("for") == 0) {
                                tokens.add(new Token("for",null,st.lineno()));
			} else if (st.sval.compareTo("hackIn") == 0) {
				tokens.add(new Token("hackIn", null,st.lineno()));
			} else if (st.sval.compareTo("hackOut") == 0) {
				tokens.add(new Token("hackOut", null,st.lineno()));
			} else if (st.sval.compareTo("if") == 0) {
				tokens.add(new Token("if", null,st.lineno()));
			} else if (st.sval.compareTo("else") == 0) {
				tokens.add(new Token("else", null,st.lineno()));
			} else if (st.sval.compareTo("to") == 0) {
				tokens.add(new Token("to", null,st.lineno()));
			} else if (st.sval.compareTo("hack") == 0){
                		tokens.add(new Token("hack",null,st.lineno()));

                        }else if (st.sval.compareTo("not") == 0){
                		tokens.add(new Token("not",null,st.lineno()));

                        }
                        
                          }//if (isAKeyWord(st.sval))
                          else if (HackXDefine.isAnID(st.sval)) {//case is the word is an ID
                                tokens.add(new Token("id", st.sval,st.lineno()));
                        }//else if (isAnID(st.sval))
                         //((isAnID(st.sval)))
                            }break;//case -3

                            default :{
                            if (st.ttype == 123) {
				tokens.add(new Token("{", null,st.lineno()));
                                openclose++;
			} else if (st.ttype == 125) {
				tokens.add(new Token("}", null,st.lineno()));
                                openclose--;
			}else if (st.ttype == 40) {
				tokens.add(new Token("(", null,st.lineno()));
			} else if (st.ttype == 41) {
				tokens.add(new Token(")", null,st.lineno()));
			}   else if (st.ttype == 62) {
                            if (st.nextToken()==61) {
                                    tokens.add(new Token(">=",null,st.lineno()));

                            }
                            else {tokens.add(new Token(">",null,st.lineno()));
                                    st.pushBack();
                            }
			}
				else if (st.ttype == 60) {
                                    if (st.nextToken()==61) {

                                    tokens.add(new Token("<=",null,st.lineno()));}
                            else {tokens.add(new Token("<",null,st.lineno()));
                                    st.pushBack();

                            }

			} else if (st.ttype == 35) {
				tokens.add(new Token("#", null,st.lineno()));
			} else if (st.ttype == 38) {
				tokens.add(new Token("&", null,st.lineno()));
			} else if (st.ttype == 124) {
				tokens.add(new Token("|", null,st.lineno()));
			} else if (st.ttype == 61) {
                                    if (st.nextToken()==61) {
                                    tokens.add(new Token("==",null,st.lineno()));}
                            else
                            {tokens.add(new Token("=",null,st.lineno()));
                                    st.pushBack();

                            }

			}
                               
                          else if (st.ttype == 43) {
				tokens.add(new Token("+", null,st.lineno()));
			} else if ((st.ttype == 45)) {
				tokens.add(new Token("-", null,st.lineno()));
			} else if (st.ttype == 42) {
				tokens.add(new Token("*", null,st.lineno()));
			} else if (st.ttype == 47) {
				tokens.add(new Token("/", null,st.lineno()));
			} else if (st.ttype == 44) {
				tokens.add(new Token(",", null,st.lineno()));
			} else if (st.ttype == 59) {
				tokens.add(new Token(";", null,st.lineno()));
			} else if (st.ttype==34 ){
                                tokens.add(new Token ("TEXT-string",st.sval,st.lineno()));
                        } else if(st.ttype==64 || st.ttype==95 ||st.ttype==37 ||st.ttype==36||st.ttype==96||st.ttype==126||st.ttype==63||st.ttype==58||st.ttype==94||st.ttype==46||st.ttype==33)//||st.ttype==10)//||[st.ttype==10]//Unknown symbols:[@]|[_]|[%]|[$]|[`]|[~]|[?]|[:]|[^]|[.]
                                 {
                                System.out.println("Lexical Error: Unknown symbol at line "+st.lineno());
                                {try{new Error ("Lexical Error: Unknown symbol at line "+st.lineno());} catch(Exception e){}}
                                   LexicalDone=false;
                        }
            break;}//defalut
            }//end for switch
                         n=st.nextToken();
            }//end for while loop
                tokens.add(new Token ("$",null,0));//End Of Tokenizing
                br.close();
                
                  int i = tokens.size();int j=0;
                  System.out.println("The Tokens are: ");
                  while (j < i) {
                      System.out.println(j+1+". Token Name: "+tokens.get(j).getName()+" Token Info: "+tokens.get(j).getInfo()+" Token Line Number: "+tokens.get(j).getLineNo());
                      j++;
                  }

        }else System.out.println("Missing File (src\\hack\\InputedCode.txt)");

Syntax.start();
Syntax.codeG=Syntax.codeG+("CODE         ENDS\n");
if(Syntax.io_out){
Syntax.codeG=Syntax.codeG+("       MOV ah, 4CH ;MS-DOS terminate opcode. \n");
Syntax.codeG=Syntax.codeG+("       INT 21h ;MS-DOS call \n");
}
Syntax.codeG=Syntax.codeG+("             END START\n");
if(Syntax.syntaxDONE&& LexicalDone )
{HackGUI.generateCode(Syntax.codeG);}

  }
}

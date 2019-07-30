package hack;

import java.util.Vector;

/**
 *
 * @author Noras Salman
 */
public class Syntax {
    public static int i;

    public static int openclose,declared=0,loopsteps,connum;
    public static boolean open=false,b=false,firstidisneg,syntaxDONE,assign,io_out,loop,con;
    public static Vector<Decleration>  var,logic,allwithtypes ;
    public static Vector<String> codeG_holdOP,codeG_holdF;
    public static String declareType,codeG,codeG_AssignHold;
   
    public static boolean IsAType (){
        if (Lexical.tokens.elementAt(i).getName().compareTo("string")==0) return true ;
        else if (Lexical.tokens.elementAt(i).getName().compareTo("integer")==0 ) return true ;
        else return false ;
    }

    public static boolean IsAId(){
        if (Lexical.tokens.elementAt(i).getName().compareTo("id")==0) return true ;
        else return false ;
    }

   public static void start(){
    syntaxDONE=false;io_out=false;loopsteps=0;loop=false;connum=0;con=false;
    codeG_holdF=new Vector<String>();
        codeG_holdOP=new Vector<String>();
       logic = new Vector<Decleration>();
       var = new Vector<Decleration>();
       allwithtypes = new Vector<Decleration>();
       i=0;codeG="";
    if (Lexical.tokens.elementAt(i).getName().compareTo("hack")==0) i++;
    else {try{int lno=Lexical.tokens.elementAt(i).getLineNo(); new Error ("Syntax Error at Line:"+lno+" program must start with the word :hack");} catch(Exception e){}} ;
    if (Lexical.tokens.elementAt(i).getName().compareTo("id")==0) i++;
    else {try{int lno=Lexical.tokens.elementAt(i).getLineNo(); new Error ("Syntax Error at Line:"+lno+" id excpected for program name");} catch(Exception e){}} ;
      if (Lexical.tokens.elementAt(i).getName().compareTo(";")==0) i++;
    else {try{int lno=Lexical.tokens.elementAt(i).getLineNo(); new Error ("Syntax Error at Line:"+lno+" ; is missing");} catch(Exception e){}} ;

   var();
   }
 public static void var(){codeG=codeG+"DATA         SEGMENT\n";
     if (!IsAType()){if (Lexical.tokens.elementAt(i).getName().compareTo("{")==0) {
         declareation_error();
         codeG=codeG+("DATA         ENDS\n");
         codeG=codeG+("STACK        SEGMENT\n");
         codeG=codeG+("             db equ 30\n");
         codeG=codeG+("STACK        ENDS\n");
         codeG=codeG+("CODE         SEGMENT\n");
         codeG=codeG+("             CODE CS:CODE,SS:STACK,DS:DATA\n");
         codeG=codeG+("START: MOV AX,STACK \n");
         codeG=codeG+("       MOV SS,AX\n");
         codeG=codeG+("       MOV AX,DATA\n");
         codeG=codeG+("       MOV DS,AX\n");
         codeG=codeG+"\n";
         logic1();block();} }
     else if (IsAType()){
         declareType=Lexical.tokens.elementAt(i).getName();
         i++; v1();}
    else {try{int lno=Lexical.tokens.elementAt(i).getLineNo(); new Error ("Syntax Error at Line:"+lno+" erro in declaration syntax");} catch(Exception e){}} ;
    }

 public static void v1(){
 if (IsAId()) {
   var.add(new Decleration(Lexical.tokens.elementAt(i).getInfo(),declareType));
    codeG=codeG+("             ")+(Lexical.tokens.elementAt(i).getInfo())+("  db 0000H\n");
 i++;
 if (Lexical.tokens.elementAt(i).getName().compareTo(";")==0) {
 i++;var();}
 else if (Lexical.tokens.elementAt(i).getName().compareTo(",")==0) {i++;v1();}}
 else {try{int lno=Lexical.tokens.elementAt(i).getLineNo(); new Error ("Syntax Error at Line:"+lno+" id expected for decleration");} catch(Exception e){}} ;
  }
 public static void block(){System.out.println("reading "+Lexical.tokens.elementAt(i).getName()+"inside blcok");
 if (Lexical.tokens.elementAt(i).getName().compareTo("{")==0) {i++; b();}
 }
 public static void b(){System.out.println("reading "+Lexical.tokens.elementAt(i).getName()+"inside b");
  if ((Lexical.tokens.elementAt(i).getName().compareTo("}")==0)&&(Lexical.tokens.elementAt(i+1).getName().compareTo("$")==0))
 {syntaxDONE=true; 
  try{int lno=Lexical.tokens.elementAt(i).getLineNo(); new Error ("No Syntax Error where found");} catch(Exception e){}}
  else if (Lexical.tokens.elementAt(i).getName().compareTo("}")==0){i++;}
  else statment();
 }
 public static void sc_check(){System.out.println("reading "+Lexical.tokens.elementAt(i).getName()+" sc_check");
 if (Lexical.tokens.elementAt(i).getName().compareTo(";")==0) {i++;}
 else {try{int lno=Lexical.tokens.elementAt(i).getLineNo(); new Error ("Syntax Error at Line:"+lno+" ; is mising");} catch(Exception e){}}
 }
public static void statment(){ System.out.println("reading "+Lexical.tokens.elementAt(i).getName()+" inside statment");
if (Lexical.tokens.elementAt(i).getName().compareTo("id")==0) {assign=true;assign();assign=false; LogicalError(Lexical.tokens.elementAt(i).getLineNo());logic.clear();
codeG=codeG+("       MOV "+codeG_AssignHold+",AX\n");codeG_AssignHold="";
GenerateTheEndOfLoopCode();sc_check(); b();}
else if (Lexical.tokens.elementAt(i).getName().compareTo("if")==0) {ifs();GenerateTheEndOfLoopCode();b();}
else if (Lexical.tokens.elementAt(i).getName().compareTo("for")==0) {loop();GenerateTheEndOfLoopCode();b();}
else if (Lexical.tokens.elementAt(i).getName().compareTo("hackIn")==0) {io();GenerateTheEndOfLoopCode();sc_check();b();}
else if (Lexical.tokens.elementAt(i).getName().compareTo("hackOut")==0) {io();GenerateTheEndOfLoopCode();sc_check();b();}
else if (Lexical.tokens.elementAt(i).getName().compareTo("else")==0 && b) { b=false;i++;rest();}
else
     {try{int lno=Lexical.tokens.elementAt(i).getLineNo(); new Error ("Syntax Error at Line:"+lno+" Unknown statment");} catch(Exception e){}}
}
public static void assign(){
    System.out.println("reading "+Lexical.tokens.elementAt(i).getName()+" inside assign");
    codeG_AssignHold="";
if (Lexical.tokens.elementAt(i).getName().compareTo("id")==0) {
    logic.add(new Decleration(Lexical.tokens.elementAt(i).getInfo(),getType(Lexical.tokens.elementAt(i).getInfo())));
    codeG_AssignHold=Lexical.tokens.elementAt(i).getInfo();
    i++;}
if (Lexical.tokens.elementAt(i).getName().compareTo("=")==0) {
 if(Lexical.tokens.elementAt(i+1).getName().compareTo("-")==0 &&
    Lexical.tokens.elementAt(i+2).getName().compareTo("id")==0 )
     codeG=codeG+("       NEG "+Lexical.tokens.elementAt(i+2).getInfo()+" \n");
 i++;
 expression();}
else
     {try{int lno=Lexical.tokens.elementAt(i).getLineNo(); new Error ("Syntax Error at Line:"+lno+" Unknown assign statment");} catch(Exception e){}}
}
public static void expression(){
    System.out.println("reading "+Lexical.tokens.elementAt(i).getName()+" inside expression");

if (Lexical.tokens.elementAt(i).getName().compareTo("+")==0) {st();}
else if (Lexical.tokens.elementAt(i).getName().compareTo("-")==0) {st();}
 else if (Lexical.tokens.elementAt(i).getName().compareTo("num")==0) {
      logic.add(new Decleration("num","integer"));

     term();factor();}
    else if (Lexical.tokens.elementAt(i).getName().compareTo("id")==0) {
    logic.add(new Decleration(Lexical.tokens.elementAt(i).getInfo(),getType(Lexical.tokens.elementAt(i).getInfo())));

    term();factor();}
     else if (Lexical.tokens.elementAt(i).getName().compareTo("TEXT-string")==0) {
         logic.add(new Decleration("TEXT-string","string"));
         term();factor();}
else
{try{int lno=Lexical.tokens.elementAt(i).getLineNo(); new Error ("Syntax Error at Line:"+lno+" + error in expression");} catch(Exception e){}}
}
public static void st(){
    if (Lexical.tokens.elementAt(i).getName().compareTo("+")==0) {i++; term();}
else if (Lexical.tokens.elementAt(i).getName().compareTo("-")==0) {

    i++; term();}
}
public static void term(){
    System.out.println("reading "+Lexical.tokens.elementAt(i).getName()+" inside term");
if (Lexical.tokens.elementAt(i).getName().compareTo("id")==0) {
  Generate();
    i++;factor();}
 else if (Lexical.tokens.elementAt(i).getName().compareTo("num")==0) {
        Generate();
     i++;factor();}
 else if (Lexical.tokens.elementAt(i).getName().compareTo("TEXT-string")==0) {i++;factor();}
 else {try{int lno=Lexical.tokens.elementAt(i).getLineNo(); new Error ("Syntax Error at Line:"+lno+" you have to input id or num in this expression");} catch(Exception e){}}

}
public static void factor(){
    System.out.println("reading "+Lexical.tokens.elementAt(i).getName()+" inside factor");
if (Lexical.tokens.elementAt(i).getName().compareTo("*")==0) {
   codeG_holdOP.add("*");
    i++;term();}
 else if (Lexical.tokens.elementAt(i).getName().compareTo("/")==0) {
   codeG_holdOP.add("/");
     i++;term();}
 else if (Lexical.tokens.elementAt(i).getName().compareTo("+")==0) {
   codeG_holdOP.add("+");
     expression();}
 else if (Lexical.tokens.elementAt(i).getName().compareTo("-")==0) {
   codeG_holdOP.add("-");
     expression();}
//else  if (Lexical.tokens.elementAt(i).getName().compareTo(";")==0) {i++;b();}
//else {try{int lno=Lexical.tokens.elementAt(i).getLineNo(); new Error ("Syntax Error at Line:"+lno+" invaild input,reinput correctly or if you are done input ';' ");} catch(Exception e){}}
}
public static void ifs(){
    System.out.println("reading "+Lexical.tokens.elementAt(i).getName()+" inside if statment");
if (Lexical.tokens.elementAt(i).getName().compareTo("if")==0) {i++;}
if (Lexical.tokens.elementAt(i).getName().compareTo("(")==0) {open=true; Z();condition();}
else {try{int lno=Lexical.tokens.elementAt(i).getLineNo(); new Error ("Syntax Error at Line:"+lno+" Condition Must start with a pracket ( ");} catch(Exception e){}}
if (openclose==0){
//if (Lexical.tokens.elementAt(i).getName().compareTo(")")==0) { i++;
R();
//}
}
 else {try{int lno=Lexical.tokens.elementAt(i).getLineNo(); new Error ("Syntax Error at Line:"+lno+" Check the Open '(' and Close ')' they must be equal counted  ");} catch(Exception e){}}
}
public static void R(){
    System.out.println("reading "+Lexical.tokens.elementAt(i).getName()+" inside R");
    b=true;
if (Lexical.tokens.elementAt(i).getName().compareTo("{")==0) {rest();}
else if (Lexical.tokens.elementAt(i).getName().compareTo("id")==0)  {rest();}
else if (Lexical.tokens.elementAt(i).getName().compareTo("if")==0) {rest();}
else if (Lexical.tokens.elementAt(i).getName().compareTo("for")==0)  {rest();}
else if (Lexical.tokens.elementAt(i).getName().compareTo("hackIn")==0)  {rest();}
else if (Lexical.tokens.elementAt(i).getName().compareTo("hackOut")==0)  {rest();}

}
public static void rest(){
    System.out.println("reading "+Lexical.tokens.elementAt(i).getName()+" inside rest");  

if (Lexical.tokens.elementAt(i).getName().compareTo("{")==0) {block();}
else if (Lexical.tokens.elementAt(i).getName().compareTo("id")==0) {statment();}
else if (Lexical.tokens.elementAt(i).getName().compareTo("if")==0){statment();}
else if (Lexical.tokens.elementAt(i).getName().compareTo("for")==0) {statment();}
else if (Lexical.tokens.elementAt(i).getName().compareTo("hackIn")==0) {statment();}
else if (Lexical.tokens.elementAt(i).getName().compareTo("hackOut")==0) {statment();}
else {try{int lno=Lexical.tokens.elementAt(i).getLineNo(); new Error ("Syntax Error at Line:"+lno+" Unknown statment ");} catch(Exception e){}}

}
public static void condition(){
    System.out.println("reading "+Lexical.tokens.elementAt(i).getName()+" condition");
bool_exp();
if (Lexical.tokens.elementAt(i).getName().compareTo("&")==0) {A();}
else if(Lexical.tokens.elementAt(i).getName().compareTo("|")==0) {A();}
else if(Lexical.tokens.elementAt(i).getName().compareTo("not")==0) {A();}
}
public static void A(){
    System.out.println("reading "+Lexical.tokens.elementAt(i).getName()+" inside A");
con_opr();
bool_exp();
}
public static void bool_exp(){connum++;con=true;
    System.out.println("reading "+Lexical.tokens.elementAt(i).getName()+" bool exp");
 if (Lexical.tokens.elementAt(i).getName().compareTo("(")==0) {open=true; Z();}
if (Lexical.tokens.elementAt(i).getName().compareTo("num")==0) {
    codeG=codeG+("       MOV AX,0"+Lexical.tokens.elementAt(i).getInfo()+"h\n");
    i++;}
    else if (Lexical.tokens.elementAt(i).getName().compareTo("id")==0) {
     codeG=codeG+("       MOV AX,"+Lexical.tokens.elementAt(i).getInfo()+"\n");   
        i++;}else  {try{int lno=Lexical.tokens.elementAt(i).getLineNo(); new Error ("Syntax Error at Line:"+lno+" IF Syntax is (IF ({id|number} BOOL_OPR {id|number|text-string})  ");} catch(Exception e){}}
  
    bool_opr();
    
if (Lexical.tokens.elementAt(i).getName().compareTo("num")==0) {
    codeG=codeG+("       MOV BX,0"+Lexical.tokens.elementAt(i).getInfo()+"h\n");
    i++;}
    else if (Lexical.tokens.elementAt(i).getName().compareTo("id")==0) {
     codeG=codeG+("       MOV BX,"+Lexical.tokens.elementAt(i).getInfo()+"\n");   
        i++;}
    else if (Lexical.tokens.elementAt(i).getName().compareTo("TEXT-string")==0) {
        codeG=codeG+("       MOV BX,'"+Lexical.tokens.elementAt(i).getInfo()+"'\n");
        i++;}
    else  {try{int lno=Lexical.tokens.elementAt(i).getLineNo(); new Error ("Syntax Error at Line:"+lno+" IF Syntax is (IF ({id|number} BOOL_OPR {id|number|text-string})  ");} catch(Exception e){}}
codeG=codeG+("       CMP AX,BX\n");
if (Lexical.tokens.elementAt(i-2).getName().compareTo("==")==0){codeG=codeG+("       JE FIRST"+connum+"\n");}
else if (Lexical.tokens.elementAt(i-2).getName().compareTo("#")==0){codeG=codeG+("       JNE FIRST"+connum+"\n");}
else if (Lexical.tokens.elementAt(i-2).getName().compareTo(">")==0){codeG=codeG+("       JA FIRST"+connum+"\n");}
else if (Lexical.tokens.elementAt(i-2).getName().compareTo(">=")==0){codeG=codeG+("       JAE FIRST"+connum+"\n");}
else if (Lexical.tokens.elementAt(i-2).getName().compareTo("<")==0){codeG=codeG+("       JB FIRST"+connum+"\n");}
else if (Lexical.tokens.elementAt(i-2).getName().compareTo("<=")==0){codeG=codeG+("       JBE FIRST"+connum+"\n");}
    LogicalError(Lexical.tokens.elementAt(i).getLineNo());logic.clear();
 codeG=codeG+("ELSE"+connum+":\n");
 codeG=codeG+("       JMP NEXT"+connum+"\n");
 codeG=codeG+("FIRST"+connum+":");
 if (Lexical.tokens.elementAt(i).getName().compareTo(")")==0 && open) { Z();}
if (Lexical.tokens.elementAt(i).getName().compareTo("&")==0) {A();}
else if(Lexical.tokens.elementAt(i).getName().compareTo("|")==0) {A();}
else if(Lexical.tokens.elementAt(i).getName().compareTo("not")==0) {A();}

}
public static void openclose(){
if (Lexical.tokens.elementAt(i).getName().compareTo("(")==0) {i++; openclose++; Z();}
else if (Lexical.tokens.elementAt(i).getName().compareTo(")")==0) {i++; openclose--; Z();}
}
public static void Z(){
    System.out.println("reading "+Lexical.tokens.elementAt(i).getName()+"inside Z");
 if (Lexical.tokens.elementAt(i).getName().compareTo("(")==0) {openclose();}
 if (Lexical.tokens.elementAt(i).getName().compareTo(")")==0) {openclose();}

}
public static void bool_opr(){
    System.out.println("reading "+Lexical.tokens.elementAt(i).getName()+" inside bool opr");
if (Lexical.tokens.elementAt(i).getName().compareTo("==")==0) {i++;}
else if (Lexical.tokens.elementAt(i).getName().compareTo("#")==0) {i++;}
else if (Lexical.tokens.elementAt(i).getName().compareTo(">")==0) {i++;}
else if (Lexical.tokens.elementAt(i).getName().compareTo("<")==0) {i++;}
else if (Lexical.tokens.elementAt(i).getName().compareTo("<=")==0) {i++;}
else if (Lexical.tokens.elementAt(i).getName().compareTo(">=")==0) {i++;}
else  {try{int lno=Lexical.tokens.elementAt(i).getLineNo(); new Error ("Syntax Error at Line:"+lno+" Unknown Boolean Operation ");} catch(Exception e){}}

}
public static void con_opr(){System.out.println("reading "+Lexical.tokens.elementAt(i).getName()+" inside con opr");
if (Lexical.tokens.elementAt(i).getName().compareTo("&")==0) {i++;}
else if (Lexical.tokens.elementAt(i).getName().compareTo("|")==0) {i++;}
else if (Lexical.tokens.elementAt(i).getName().compareTo("not")==0) {i++;}
else  {try{int lno=Lexical.tokens.elementAt(i).getLineNo(); new Error ("Syntax Error at Line:"+lno+" Unknown Condition Operation Operation ");} catch(Exception e){}}

}

public static void loop(){loopsteps++;loop=true;
System.out.println("reading "+Lexical.tokens.elementAt(i).getName()+" loop");
if (Lexical.tokens.elementAt(i).getName().compareTo("for")==0) {i++;}
if (Lexical.tokens.elementAt(i).getName().compareTo("id")==0) {i++;}
else  {try{int lno=Lexical.tokens.elementAt(i).getLineNo(); new Error ("Syntax Error at Line:"+lno+" Loop must start with (id) ");} catch(Exception e){}}
if (Lexical.tokens.elementAt(i).getName().compareTo("=")==0) {i++;}
else  {try{int lno=Lexical.tokens.elementAt(i).getLineNo(); new Error ("Syntax Error at Line:"+lno+" Loop must start with (id)= ");} catch(Exception e){}}
if (Lexical.tokens.elementAt(i).getName().compareTo("num")==0) {i++;}
else if (Lexical.tokens.elementAt(i).getName().compareTo("id")==0) {i++;}else  {try{int lno=Lexical.tokens.elementAt(i).getLineNo(); new Error ("Syntax Error at Line:"+lno+" Loop Syntax is (for id={id|number} to {id|number})  ");} catch(Exception e){}}
if (Lexical.tokens.elementAt(i).getName().compareTo("to")==0) {i++;}
if (Lexical.tokens.elementAt(i).getName().compareTo("num")==0) {i++;}
else if (Lexical.tokens.elementAt(i).getName().compareTo("id")==0) {i++;}else  {try{int lno=Lexical.tokens.elementAt(i).getLineNo(); new Error ("Syntax Error at Line:"+lno+" Loop Syntax is (for id={id|number} to {id|number})  ");} catch(Exception e){}}
codeG=codeG+"\n";
codeG=codeG+("       MOV AX,"+Lexical.tokens.elementAt(i-3).getInfo()+" ;  calculate the (Counter) Steps \n");
codeG=codeG+("       MOV CX,"+Lexical.tokens.elementAt(i-1).getInfo()+" ;  calculate the (Counter) Steps \n");
codeG=codeG+("       SUB CX,AX ; CX=CX-AX (the COUNTER STEPS in CX) \n");
codeG=codeG+("BACK"+loopsteps+":");
rest();

}
public static void io(){
if (Lexical.tokens.elementAt(i).getName().compareTo("hackIn")==0) {io_out=true;i++;{
if (Lexical.tokens.elementAt(i).getName().compareTo("(")==0){i++;
if (Lexical.tokens.elementAt(i).getName().compareTo("id")==0){
      codeG=codeG+("       ORG 0010h ;  =hackIn \n");
      codeG=codeG+("       DATA1 DB 24,?,24 DUP(0FFh) ;set 24byte and (?) until enter \n");
      codeG=codeG+("       MOV AH, 0AH \n");
      codeG=codeG+("       MOV DX, OFFSET DATA1 \n");
      codeG=codeG+("       INT 21H \n");
      codeG=codeG+("       MOV "+Lexical.tokens.elementAt(i).getInfo()+" , OFFSET DATA1 \n");
    i++;
if (Lexical.tokens.elementAt(i).getName().compareTo(")")==0){i++;}
else  {try{int lno=Lexical.tokens.elementAt(i).getLineNo(); new Error ("Syntax Error at Line:"+lno+" closed bracket needed for input ");} catch(Exception e){}}
}
else  {try{int lno=Lexical.tokens.elementAt(i).getLineNo(); new Error ("Syntax Error at Line:"+lno+" id parameter needed for input ");} catch(Exception e){}}
}
else  {try{int lno=Lexical.tokens.elementAt(i).getLineNo(); new Error ("Syntax Error at Line:"+lno+" open Bracket needed for input ( ");} catch(Exception e){}}
}
}
else if (Lexical.tokens.elementAt(i).getName().compareTo("hackOut")==0) {io_out=true;i++;{
if (Lexical.tokens.elementAt(i).getName().compareTo("(")==0){i++;
if (Lexical.tokens.elementAt(i).getName().compareTo("id")==0){
      codeG=codeG+("       MOV AH, 09H ; =hackOut\n");
      codeG=codeG+("       MOV DX, "+Lexical.tokens.elementAt(i).getInfo()+" \n");
      codeG=codeG+("       INT 21H \n");
    expression();}
else {codeG=codeG+("       DATA1 DB '"+Lexical.tokens.elementAt(i).getInfo()+"', 13, 10, '$' \n");
      codeG=codeG+("       MOV AH, 09H ;=hackOut\n");
      codeG=codeG+("       MOV DX, OFFSET DATA1 \n");
      codeG=codeG+("       INT 21H \n");

    expression();}
if (Lexical.tokens.elementAt(i).getName().compareTo(")")==0){i++;}
else  {try{int lno=Lexical.tokens.elementAt(i).getLineNo(); new Error ("Syntax Error at Line:"+lno+" closed bracket needed for output ");} catch(Exception e){}}

}
else  {try{int lno=Lexical.tokens.elementAt(i).getLineNo(); new Error ("Syntax Error at Line:"+lno+" open Bracket needed for output ( ");} catch(Exception e){}}
}
}
}
public static void logic1(){
int c=0;
    int idcount=0;
     for (int j=2;j<Lexical.tokens.size();j++)
if (Lexical.tokens.elementAt(j).getName().compareTo("id")==0)
    idcount++;
      String[] ids=new String[idcount];
    for (int j=2;j<Lexical.tokens.size();j++)
if (Lexical.tokens.elementAt(j).getName().compareTo("id")==0)
{ids[c]=Lexical.tokens.elementAt(j).getInfo(); c++;}

     for (int j=0;j<ids.length;j++){
for (int k=0;k<var.size();k++){
if (ids[j].compareTo(var.elementAt(k).id_val)==0)
{ allwithtypes.add(new Decleration(ids[j],var.elementAt(k).type));}
}
}
}
public static void declareation_error(){
    int c=0;
    int idcount=0;
     for (int j=2;j<Lexical.tokens.size();j++)
if (Lexical.tokens.elementAt(j).getName().compareTo("id")==0)
    idcount++;
      String[] ids=new String[idcount];
     boolean[] isDeclared= new boolean[idcount];
    for (int j=2;j<Lexical.tokens.size();j++)
if (Lexical.tokens.elementAt(j).getName().compareTo("id")==0)
{ids[c]=Lexical.tokens.elementAt(j).getInfo(); isDeclared[c]=false;c++;}

     for (int j=0;j<ids.length;j++){
for (int k=0;k<var.size();k++){
if (ids[j].compareTo(var.elementAt(k).id_val)==0)
{isDeclared[j]=true; }
}
}
for (int k=0;k<isDeclared.length;k++)
    if (isDeclared[k]==false)
{try{new Error ("Sementic Error: The Variabel( "+ids[k]+" )used and not declared");} catch(Exception e){}}
  }

public static String getType(String id1){
    boolean k=false;String temp = null;
for(int j=0;j<allwithtypes.size();j++)
if(allwithtypes.elementAt(j).getVal().compareTo(id1)==0)
{k=true; temp=allwithtypes.elementAt(j).getType();}
if (k) return temp;
else return null;
}

public static void LogicalError(int line){
for (int j=0;j<logic.size();j++)
if (logic.elementAt(j).getType().compareTo(logic.elementAt(0).type)!=0)
    try{new Error ("Logical Error:at line "+line+" Assining variabel( "+logic.elementAt(0).id_val+" ) =( "+logic.elementAt(j).getVal()+" )must be("+logic.elementAt(0).type+")");} catch(Exception e){}}


public static void Generate(){
 /* if (Lexical.tokens.elementAt(i).getName().compareTo("id")==0)
    if(assign){
    if(Lexical.tokens.elementAt(i+1).getName().compareTo(";")!=0 ||
           (Lexical.tokens.elementAt(i-1).getName().compareTo("=")!=0)){
    codeG=codeG+("       MOV AL,"+Lexical.tokens.elementAt(i).getInfo()+"\n");
    codeG=codeG+("       PUSH AL \n");pushcount++;
    if(pushcount==2)GenerateOP(codeG_holdOP.firstElement());}else codeG=codeG+("       MOV AX,"+Lexical.tokens.elementAt(i).getInfo()+"\n");}
else if(Lexical.tokens.elementAt(i).getName().compareTo("num")==0)
  if(assign){
    if(Lexical.tokens.elementAt(i+1).getName().compareTo(";")!=0 ||
           (Lexical.tokens.elementAt(i-1).getName().compareTo("=")!=0)){
    codeG=codeG+("       MOV AL,0"+Lexical.tokens.elementAt(i).getInfo()+"h\n");
    codeG=codeG+("       PUSH AL \n");pushcount++;
    if(pushcount==2)GenerateOP(codeG_holdOP.firstElement());}else codeG=codeG+("       MOV AX,0"+Lexical.tokens.elementAt(i).getInfo()+"h\n");}
*/


    if (Lexical.tokens.elementAt(i).getName().compareTo("id")==0){if(assign){
if(Lexical.tokens.elementAt(i+1).getName().compareTo(";")!=0 ||
           (Lexical.tokens.elementAt(i-1).getName().compareTo("=")!=0)){
    push();
    if(codeG_holdF.size()==2 && (codeG_holdOP.firstElement().compareTo("*")==0 || codeG_holdOP.firstElement().compareTo("/")==0 ))
    {GenerateOP2(codeG_holdOP.firstElement());}
    else  if(codeG_holdF.size()==2 && (codeG_holdOP.firstElement().compareTo("+")==0 || codeG_holdOP.firstElement().compareTo("-")==0 ))
    {GenerateOP2(codeG_holdOP.firstElement());}
    else if(Lexical.tokens.elementAt(i+1).getName().compareTo(";")==0 && codeG_holdF.size()==1 && (codeG_holdOP.firstElement().compareTo("*")==0 || codeG_holdOP.firstElement().compareTo("/")==0 ))
    {GenerateOP1(codeG_holdOP.firstElement());}
    else  if(Lexical.tokens.elementAt(i+1).getName().compareTo(";")==0 && codeG_holdF.size()==1 && (codeG_holdOP.firstElement().compareTo("+")==0 || codeG_holdOP.firstElement().compareTo("-")==0 ))
    {GenerateOP1(codeG_holdOP.firstElement());}
    }else codeG=codeG+("       MOV AX,"+Lexical.tokens.elementAt(i).getInfo()+"\n");}}
    
    else  if (Lexical.tokens.elementAt(i).getName().compareTo("num")==0){if(assign){
if(Lexical.tokens.elementAt(i+1).getName().compareTo(";")!=0 ||
           (Lexical.tokens.elementAt(i-1).getName().compareTo("=")!=0)){
    push();
    if(codeG_holdF.size()==2 && (codeG_holdOP.firstElement().compareTo("*")==0 || codeG_holdOP.firstElement().compareTo("/")==0 ))
    {GenerateOP2(codeG_holdOP.firstElement());}
    else  if(codeG_holdF.size()==2 && (codeG_holdOP.firstElement().compareTo("+")==0 || codeG_holdOP.firstElement().compareTo("-")==0 ))
    {GenerateOP2(codeG_holdOP.firstElement());}
    else if(Lexical.tokens.elementAt(i+1).getName().compareTo(";")==0 && codeG_holdF.size()==1 && (codeG_holdOP.firstElement().compareTo("*")==0 || codeG_holdOP.firstElement().compareTo("/")==0 ))
    {GenerateOP1(codeG_holdOP.firstElement());}
    else  if(Lexical.tokens.elementAt(i+1).getName().compareTo(";")==0 && codeG_holdF.size()==1 && (codeG_holdOP.firstElement().compareTo("+")==0 || codeG_holdOP.firstElement().compareTo("-")==0 ))
    {GenerateOP1(codeG_holdOP.firstElement());}
    }else codeG=codeG+("       MOV AX,0"+Lexical.tokens.elementAt(i).getInfo()+"h\n");}}

}

public static void push(){
codeG_holdF.add(Lexical.tokens.elementAt(i).getInfo());
if(Lexical.tokens.elementAt(i).getName().compareTo("id")==0)
     codeG=codeG+("       MOV AL,"+Lexical.tokens.elementAt(i).getInfo()+"\n");
else codeG=codeG+("       MOV AL,0"+Lexical.tokens.elementAt(i).getInfo()+"h\n");
codeG=codeG+("       PUSH AL \n");
}
public static void popF(){
    codeG_holdF.remove(0);

}
public static void popOP(){
    codeG_holdOP.remove(0);
}
public static void MUL2(){
 codeG=codeG+("       POP AL \n");
    codeG=codeG+("       POP BL \n");
    codeG=codeG+("       IMUL BL ; AX=AL*BL \n");
}
public static void DIV2(){
 codeG=codeG+("       POP AL \n");
    codeG=codeG+("       POP BL \n");
    codeG=codeG+("       IDIV BL ; AX=AL/BL \n");
}
public static void SUB2(){
 codeG=codeG+("       POP BX \n");
    codeG=codeG+("       POP DX \n");
     codeG=codeG+("       SUB BX,DX ; BX=BX+DX \n");
    codeG=codeG+("       ADD AX,BX ; AX=AX+BX \n");
}
public static void ADD2(){
 codeG=codeG+("       POP BX \n");
    codeG=codeG+("       POP DX \n");
    codeG=codeG+("       ADD BX,DX ; BX=BX+DX \n");
    codeG=codeG+("       ADD AX,BX ; AX=AX+BX \n");
}
public static void GenerateOP2(String s){
if (s.compareTo("*")==0)MUL2();
else if (s.compareTo("/")==0)DIV2();
else if (s.compareTo("+")==0)ADD2();
else if (s.compareTo("-")==0)SUB2();
//codeG_holdOP.removeElementAt(0);
popF();popF();popOP();
}
public static void MUL1(){
    codeG=codeG+("       POP BL \n");
    codeG=codeG+("       MOV AL,AX \n");
    codeG=codeG+("       IMUL BL ; AX=AX*BL \n");
}
public static void DIV1(){
    codeG=codeG+("       POP BL \n");
    codeG=codeG+("       MOV AL,AX \n");
    codeG=codeG+("       IDIV BL ; AX=ax/BL \n");
}
public static void SUB1(){
    codeG=codeG+("       POP BX \n");
    codeG=codeG+("       SUB AX,BX ; AX=AX-BX \n");
}
public static void ADD1(){
 codeG=codeG+("       POP BX \n");
    codeG=codeG+("       ADD AX,BX ; AX=AX+BX \n");
}
public static void GenerateOP1(String s){
if (s.compareTo("*")==0)MUL1();
else if (s.compareTo("/")==0)DIV1();
else if (s.compareTo("+")==0)ADD1();
else if (s.compareTo("-")==0)SUB1();
//codeG_holdOP.removeElementAt(0);
popF();popOP();
}
public static void GenerateTheEndOfLoopCode(){
GenerateTheEndOfConditionCode();
    if (loop){loop=false;
codeG=codeG+("       DEC CX    ;DECREMENT COUNTER \n");
codeG=codeG+("JNZ BACK"+loopsteps+"     ;LOOP IF NOT FINISHED CX=?0 \n");
codeG=codeG+"\n";
}
}
public static void GenerateTheEndOfConditionCode(){
if (con){con=false;
    codeG=codeG+("NEXT"+connum+":");}
}
}
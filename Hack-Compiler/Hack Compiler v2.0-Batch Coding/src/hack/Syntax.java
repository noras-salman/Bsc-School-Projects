package hack;

import java.util.Vector;

/**
 *
 * @author Noras Salman
 */
public class Syntax {
    public static int i;

    public static int openclose,declared=0,loopsteps,ifnum=0,connum=1,holdls,nestedloops;
    public static boolean open=false,b=false,firstidisneg,syntaxDONE,assign,io_out,loop,con,isfor=false,c;
    public static Vector<Decleration>  var,logic,allwithtypes ;
    public static Vector<String> codeG_holdOP,codeG_holdF;
    public static String declareType,codeG,codeG_AssignHold,program_name,holdnamei,namei,vali,valto;
   
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
    syntaxDONE=false;io_out=false;loopsteps=0;loop=false;connum=0;con=false;nestedloops=0;ifnum=0;connum=1;
    codeG_holdF=new Vector<String>();c=false;
        codeG_holdOP=new Vector<String>();
       logic = new Vector<Decleration>();
       var = new Vector<Decleration>();
       allwithtypes = new Vector<Decleration>();
       i=0;codeG="";
       codeG=codeG+"@echo off\ncls\ncolor A\necho. This program is generated by (HACK) Compiler\necho. \"    --------------------------\necho. \"    | H   H AAAAA CCCCC K  K |\necho. \"    | H   H AAAAA CCCCC K  K |\necho. \"    | HHHHH A   A C     K K  |  Noras Salman\necho. \"    | HHHHH AAAAA C     KK   |                \necho. \"    | H   H A   A C     K K  |\necho. \"    | H   H A   A CCCCC K  K |\necho. \"    --------------------------\necho. \" .............................................\necho.\n";
       codeG=codeG+"echo. Program Name:";
    if (Lexical.tokens.elementAt(i).getName().compareTo("hack")==0) i++;
    else {try{int lno=Lexical.tokens.elementAt(i).getLineNo(); new Error ("Syntax Error at Line:"+lno+" program must start with the word :hack");} catch(Exception e){}} ;
    if (Lexical.tokens.elementAt(i).getName().compareTo("id")==0) {
        program_name=Lexical.tokens.elementAt(i).getInfo();
        codeG=codeG+"(( "+Lexical.tokens.elementAt(i).getInfo()+".bat ))"+"\n";
        codeG=codeG+"echo path:\ncd\necho.\n";i++;}
    else {try{int lno=Lexical.tokens.elementAt(i).getLineNo(); new Error ("Syntax Error at Line:"+lno+" id excpected for program name");} catch(Exception e){}} ;
      if (Lexical.tokens.elementAt(i).getName().compareTo(";")==0) i++;
    else {try{int lno=Lexical.tokens.elementAt(i).getLineNo(); new Error ("Syntax Error at Line:"+lno+" ; is missing");} catch(Exception e){}} ;

   var();
   }
 public static void var(){codeG=codeG+"::DATA Declaring Variabels\n@echo off\n";
     if (!IsAType()){if (Lexical.tokens.elementAt(i).getName().compareTo("{")==0) {
         declareation_error();
         codeG=codeG+("echo. Execution:\necho.\n");
         logic1();block();}
             else {try{int lno=Lexical.tokens.elementAt(i).getLineNo(); new Error ("Syntax Error at Line:"+lno+" Error in declaration syntax");} catch(Exception e){}} ;
     }
     else if (IsAType()){
         declareType=Lexical.tokens.elementAt(i).getName();
         i++; v1();}
    else {try{int lno=Lexical.tokens.elementAt(i).getLineNo(); new Error ("Syntax Error at Line:"+lno+" erro in declaration syntax");} catch(Exception e){}} ;
    }

 public static void v1(){
 if (IsAId()) {
   var.add(new Decleration(Lexical.tokens.elementAt(i).getInfo(),declareType));
    codeG=codeG+("set /a math=")+(Lexical.tokens.elementAt(i).getInfo())+("=0\n");
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
public static void statment(){
    System.out.println("reading "+Lexical.tokens.elementAt(i).getName()+" inside statment");
    generateEndofIF();
if (Lexical.tokens.elementAt(i).getName().compareTo("id")==0) {assign=true;assign();assign=false; LogicalError(Lexical.tokens.elementAt(i).getLineNo());logic.clear();
sc_check();generateEndLoop(); b();}
else if (Lexical.tokens.elementAt(i).getName().compareTo("if")==0) {ifs();generateEndLoop();b();}
else if (Lexical.tokens.elementAt(i).getName().compareTo("for")==0) {loop();b();}
else if (Lexical.tokens.elementAt(i).getName().compareTo("hackIn")==0) {io();generateEndLoop();sc_check();b();}
else if (Lexical.tokens.elementAt(i).getName().compareTo("hackOut")==0) {io();generateEndLoop();sc_check();b();}
else if (Lexical.tokens.elementAt(i).getName().compareTo("else")==0 && b) { 
 codeG=codeG+("goto endif"+ifnum+"\n:else"+ifnum+"\n");
    b=false;i++;rest();
codeG=codeG+(":endif"+ifnum+"\n");
}
else
     {try{int lno=Lexical.tokens.elementAt(i).getLineNo(); new Error ("Syntax Error at Line:"+lno+" Unknown statment");} catch(Exception e){}}
}
public static void assign(){
    System.out.println("reading "+Lexical.tokens.elementAt(i).getName()+" inside assign");

if (Lexical.tokens.elementAt(i).getName().compareTo("id")==0) {
    logic.add(new Decleration(Lexical.tokens.elementAt(i).getInfo(),getType(Lexical.tokens.elementAt(i).getInfo())));
    generateAssign();
    i++;}
if (Lexical.tokens.elementAt(i).getName().compareTo("=")==0) {i++;
 if(Lexical.tokens.elementAt(i+1).getName().compareTo("-")==0 &&
    Lexical.tokens.elementAt(i+2).getName().compareTo("id")==0 )
 i++;
 expression();gelse();}
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
    i++;factor();}
 else if (Lexical.tokens.elementAt(i).getName().compareTo("num")==0) {    
     i++;factor();}
 else if (Lexical.tokens.elementAt(i).getName().compareTo("TEXT-string")==0) {i++;factor();}
 else {try{int lno=Lexical.tokens.elementAt(i).getLineNo(); new Error ("Syntax Error at Line:"+lno+" you have to input id or num in this expression");} catch(Exception e){}}

}
public static void factor(){
    System.out.println("reading "+Lexical.tokens.elementAt(i).getName()+" inside factor");
if (Lexical.tokens.elementAt(i).getName().compareTo("*")==0) {
 
    i++;term();}
 else if (Lexical.tokens.elementAt(i).getName().compareTo("/")==0) {
     i++;term();}
 else if (Lexical.tokens.elementAt(i).getName().compareTo("+")==0) {
     expression();}
 else if (Lexical.tokens.elementAt(i).getName().compareTo("-")==0) {
     expression();}
//else  if (Lexical.tokens.elementAt(i).getName().compareTo(";")==0) {i++;b();}
//else {try{int lno=Lexical.tokens.elementAt(i).getLineNo(); new Error ("Syntax Error at Line:"+lno+" invaild input,reinput correctly or if you are done input ';' ");} catch(Exception e){}}
}
public static void ifs(){con=true;
    System.out.println("reading "+Lexical.tokens.elementAt(i).getName()+" inside if statment");
if (Lexical.tokens.elementAt(i).getName().compareTo("if")==0) {codeG=codeG+("if ");i++;}
if (Lexical.tokens.elementAt(i).getName().compareTo("(")==0) {open=true; Z();condition();}
else {try{int lno=Lexical.tokens.elementAt(i).getLineNo(); new Error ("Syntax Error at Line:"+lno+" Condition Must start with a pracket ( ");} catch(Exception e){}}
if (openclose==0){

R();
//codeG=codeG+("goto endif1
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
public static void bool_exp(){connum++;con=true;ifnum++;c=true;
    System.out.println("reading "+Lexical.tokens.elementAt(i).getName()+" bool exp");
 if (Lexical.tokens.elementAt(i).getName().compareTo("(")==0) {open=true; Z();}
if (Lexical.tokens.elementAt(i).getName().compareTo("num")==0) {
    codeG=codeG+("%"+Lexical.tokens.elementAt(i).getInfo()+"% ");
    i++;}
    else if (Lexical.tokens.elementAt(i).getName().compareTo("id")==0) {
     codeG=codeG+("%"+Lexical.tokens.elementAt(i).getInfo()+"% ");
        i++;}else  {try{int lno=Lexical.tokens.elementAt(i).getLineNo(); new Error ("Syntax Error at Line:"+lno+" IF Syntax is (IF ({id|number} BOOL_OPR {id|number|text-string})  ");} catch(Exception e){}}
  
    bool_opr();
    
if (Lexical.tokens.elementAt(i).getName().compareTo("num")==0) {
   codeG=codeG+("%"+Lexical.tokens.elementAt(i).getInfo()+"% ");
    i++;}
    else if (Lexical.tokens.elementAt(i).getName().compareTo("id")==0) {
     codeG=codeG+("%"+Lexical.tokens.elementAt(i).getInfo()+"% ");
        i++;}
    else if (Lexical.tokens.elementAt(i).getName().compareTo("TEXT-string")==0) {
        codeG=codeG+(" "+Lexical.tokens.elementAt(i).getInfo()+" ");
        i++;}
    else  {try{int lno=Lexical.tokens.elementAt(i).getLineNo(); new Error ("Syntax Error at Line:"+lno+" IF Syntax is (IF ({id|number} BOOL_OPR {id|number|text-string})  ");} catch(Exception e){}}

    LogicalError(Lexical.tokens.elementAt(i).getLineNo());logic.clear();

 if (Lexical.tokens.elementAt(i).getName().compareTo(")")==0 && open) { Z();}
if (Lexical.tokens.elementAt(i).getName().compareTo("&")==0) {codeG=codeG+(" (\n goto con"+connum+" )\n:con"+connum+"\nif ");A();}
else if(Lexical.tokens.elementAt(i).getName().compareTo("|")==0) {codeG=codeG+(" (\n goto istrue"+ifnum+")\nif ");A();}
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
if (Lexical.tokens.elementAt(i).getName().compareTo("==")==0) {codeG=codeG+(" equ ");i++;}
else if (Lexical.tokens.elementAt(i).getName().compareTo("#")==0) {codeG=codeG+(" neq ");i++;}
else if (Lexical.tokens.elementAt(i).getName().compareTo(">")==0) {codeG=codeG+(" gtr ");i++;}
else if (Lexical.tokens.elementAt(i).getName().compareTo("<")==0) {codeG=codeG+(" geq ");i++;}
else if (Lexical.tokens.elementAt(i).getName().compareTo("<=")==0) {codeG=codeG+(" lss ");i++;}
else if (Lexical.tokens.elementAt(i).getName().compareTo(">=")==0) {codeG=codeG+(" leq ");i++;}
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

namei=Lexical.tokens.elementAt(i-5).getInfo();
vali=Lexical.tokens.elementAt(i-3).getInfo();
valto=Lexical.tokens.elementAt(i-1).getInfo();

codeG=codeG+("set /a "+namei+"="+vali+"\n");
codeG=codeG+("set /a count"+loopsteps+"="+valto+"\n");
codeG=codeG+(":loop"+loopsteps+"\n");nestedloops++;
if (Lexical.tokens.elementAt(i).getName().compareTo("for")==0) {holdls=loopsteps;holdnamei=namei;isfor=true;nestedloops++;}
rest();
 
}
public static void io(){
if (Lexical.tokens.elementAt(i).getName().compareTo("hackIn")==0) {io_out=true;i++;{
if (Lexical.tokens.elementAt(i).getName().compareTo("(")==0){i++;
if (Lexical.tokens.elementAt(i).getName().compareTo("id")==0){
 codeG=codeG+("set /p "+Lexical.tokens.elementAt(i).getInfo()+"=\n");
    i++;
if (Lexical.tokens.elementAt(i).getName().compareTo(")")==0){gelse();i++;}
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
    if (getType(Lexical.tokens.elementAt(i).getInfo()).compareTo("integer")==0)
    codeG=codeG+("set /a math=out=");
    else codeG=codeG+("set out=%");
      int g=i;
while (Lexical.tokens.elementAt(g).getName().compareTo(")")!=0)
{if(Lexical.tokens.elementAt(g).getName().compareTo("id")==0 || Lexical.tokens.elementAt(g).getName().compareTo("num")==0)
 codeG=codeG+(Lexical.tokens.elementAt(g).getInfo());
 else
 codeG=codeG+(Lexical.tokens.elementAt(g).getName());
 g++;
}
      if (getType(Lexical.tokens.elementAt(i).getInfo()).compareTo("string")==0)
    codeG=codeG+("%\n");else codeG=codeG+("\n");
    codeG=codeG+("echo %out%\n");
    expression();}
else if (Lexical.tokens.elementAt(i).getName().compareTo("TEXT-string")==0)
{codeG=codeG+("echo "+Lexical.tokens.elementAt(i).getInfo()+"\n");i++;
}
if (Lexical.tokens.elementAt(i).getName().compareTo(")")==0){gelse();i++;}
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

public static void generateAssign(){

    if (getType(Lexical.tokens.elementAt(i).getInfo()).compareTo("integer")==0){
     codeG=codeG+("set /a math=");
      int g=i;
while (Lexical.tokens.elementAt(g).getName().compareTo(";")!=0)
{if(Lexical.tokens.elementAt(g).getName().compareTo("id")==0 || Lexical.tokens.elementAt(g).getName().compareTo("num")==0)
 codeG=codeG+(Lexical.tokens.elementAt(g).getInfo());
 else
 codeG=codeG+(Lexical.tokens.elementAt(g).getName());
 g++;
}
     }
     else {codeG=codeG+("set \"");
            int g=i;
while (Lexical.tokens.elementAt(g).getName().compareTo(";")!=0)
{if (Lexical.tokens.elementAt(g).getName().compareTo("id")==0&&g==i)
 {codeG=codeG+(Lexical.tokens.elementAt(g).getInfo())+"=";g++;}
 else
 if (Lexical.tokens.elementAt(g).getName().compareTo("TEXT-string")==0)
 codeG=codeG+(Lexical.tokens.elementAt(g).getInfo());
 else if (Lexical.tokens.elementAt(g).getName().compareTo("id")==0&&g!=i)
     codeG=codeG+("%"+Lexical.tokens.elementAt(g).getInfo()+"%");
     else if (Lexical.tokens.elementAt(g).getName().compareTo("+")!=0)
      {try{int lno=Lexical.tokens.elementAt(g).getLineNo(); new Error ("Syntax Error at Line:"+lno+" You Can Only use (+=Concat) with Strings ");} catch(Exception e){}}
 g++;
}
 codeG=codeG+("\"");
     }


codeG=codeG+"\n";
}

public static void generateEndLoop(){
    if (loop){
if(!isfor&&(nestedloops%2)==0){

codeG=codeG+("if %"+namei+"%==%count"+loopsteps+"% goto end"+loopsteps+"\n");
codeG=codeG+("set /a "+namei+"="+namei+"+1\n");
codeG=codeG+"goto loop"+loopsteps+"\n";
codeG=codeG+":end"+loopsteps+"\n";}
else if(isfor){

codeG=codeG+("if %"+namei+"%==%count"+loopsteps+"% goto end"+loopsteps+"\n");
codeG=codeG+("set /a "+namei+"="+namei+"+1\n");
codeG=codeG+"goto loop"+loopsteps+"\n";
codeG=codeG+":end"+loopsteps+"\n";


codeG=codeG+("if %"+holdnamei+"%==%count"+holdls+"% goto end"+holdls+"\n");
codeG=codeG+("set /a "+holdnamei+"="+holdnamei+"+1\n");
codeG=codeG+"goto loop"+holdls+"\n";
codeG=codeG+":end"+holdls+"\n";isfor=false;
}
else  if(!isfor&&(nestedloops)==1){
codeG=codeG+("if %"+namei+"%==%count"+loopsteps+"% goto end"+loopsteps+"\n");
codeG=codeG+("set /a "+namei+"="+namei+"+1\n");
codeG=codeG+"goto loop"+loopsteps+"\n";
codeG=codeG+":end"+loopsteps+"\n";}loop=false;
}}
public static void generateEndofIF(){
  if (con){codeG=codeG+(" ( goto istrue"+ifnum+" ) else ( goto else"+ifnum+" )\n:istrue"+ifnum+"\n");con=false;}}
public static void gelse(){System.out.println("gelse");
if (c){codeG=codeG+(":else"+ifnum+"\n");c=false;}
}
}
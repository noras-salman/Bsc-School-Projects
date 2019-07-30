%% Affine Cipher By Noras Salman 
%% E(x)=(a*x+b) mod n
clc
clear
key=[5 7];
Mul_key=key(1,1);
Add_key=key(1,2);
index=['a':'z'];
cipherAlphabet=['A':'Z'];
plainText='defendtheeastwall';
cipherText=[];
for i=1:1:length(plainText)
    p=find(index==plainText(i));
    c=mod((Mul_key*(p-1)+Add_key),26);
    cipherText=[cipherText cipherAlphabet(c+1)];
end;


%%D (x)=a^-1 * (x+b^-1) mod n
Mul_key=5;
Add_key=7;
index=['a':'z'];
cipherAlphabet=['A':'Z'];
cipherText
plainText=[];
for i=1:1:length(cipherText)
    c=find(cipherAlphabet==cipherText(i));
    Mul_Key_MMI=findMMI(Mul_key);
    p=mod(Mul_Key_MMI*((c-1)-Add_key),26);
    plainText=[plainText index(p+1)];
end;
plainText
clc
clear
key=12;
plainText='attackistoday';

index=['a':'z'];
cipherAlphabet=['A':'Z'];

cipherText=[];
k=0;
for i=1:1:length(plainText)
    p=find(index==plainText(i));
    c=mod(((p-1)+key),26);
    cipherText=[cipherText cipherAlphabet(c+1)];
    k=k+1;
    k1=find(index==plainText(k));
    key=k1-1;
end;

key=12;
cipherText

index=['a':'z'];
cipherAlphabet=['A':'Z'];

plainText=[];
for i=1:1:length(cipherText)
    c=find(cipherAlphabet==cipherText(i));
    p=mod(((c-1)-key),26);
    plainText=[plainText index(p+1)];
    key=p;
end;
plainText
clc
clear
index=['a':'z'];
cipherAlphabet=['A':'Z'];
key=3;
plainText='book';
cipherText=[];
for i=1:1:length(plainText)
    p=find(index==plainText(i));
    c=mod(((p-1)+key),26);
    cipherText=[cipherText cipherAlphabet(c+1)];
end;



index=['a':'z'];
cipherAlphabet=['A':'Z'];
key=3;
cipherText
plainText=[];
for i=1:1:length(cipherText)
    c=find(cipherAlphabet==cipherText(i));
    p=mod(((c-1)-key),26);
    plainText=[plainText index(p+1)];
end;
plainText
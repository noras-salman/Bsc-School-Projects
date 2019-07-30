clc
clear
index=['a':'z'];
cipherAlphabet=['A':'Z'];
key='pascal';
plainText='sheislistening';
cipherText=[];
k=0;

for i=1:1:length(plainText)
    p=find(index==plainText(i));
    key1=key(mod(k,length(key))+1);% the Carchater
    key11=find(index==key1)-1;     %the Place of the Carchater
    c=mod(((p-1)+key11),26);
    cipherText=[cipherText cipherAlphabet(c+1)];
    k=k+1;
end;
cipherText

index=['a':'z'];
cipherAlphabet=['A':'Z'];
key='pascal';
plainText='';
k=0;

for i=1:1:length(cipherText)
    c=find(cipherAlphabet==cipherText(i));
    key1=key(mod(k,length(key))+1);% the Carchater
    key11=find(index==key1)-1;     %the Place of the Carchater
    p=mod(((c-1)-key11),26);
    plainText=[plainText index(p+1)];
    k=k+1;
end;
plainText
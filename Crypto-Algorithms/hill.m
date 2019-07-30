% ====Hill Cipher===
clc
clear
key=[2 3;7 8];
plainText='noanswer';
[m,n]=size(key)

%making diagrafs
diagrafs=[];
r=floor(length(plainText)/m)+mod(length(plainText),m);%num of rows
for i=1:1:r
    for j=1:1:m
  diagrafs(i,j)='x';  
    end;
end;
char(diagrafs)
%=======================================================
i=1;j=1;k=1;%diagrafs(k,j)

while ((k<=r) && (i<=length(plainText)))
    if j==m+1
        j=1
        k=k+1;
    end;
 diagrafs(k,j)=char(plainText(i)) ;  
 j=j+1;
 i=i+1;
  
end;
index=['a':'z'];
cipherAlphabet=['A':'Z'];


%making numeric
for i=1:1:r
    for j=1:1:m
     p=find(index==diagrafs(i,j)); 
    diagrafs(i,j)=p-1;
    end;
end;
P=diagrafs
key
C=P*key
C=mod(C,26)
C2=C
for i=1:1:r
    for j=1:1:m
    C(i,j)=index(C(i,j)+1);
    end;
end;
char(C)

k=1;
for i=1:1:r
    for j=1:1:m
    cipherText(k)=C(i,j);
    k=k+1;
    end;
end;
char(cipherText)


%enqrypt
key
detKey=det(key)
detKey=findMMI(detKey)
invKey=adj(key)*detKey;
invKey=mod(invKey,26)

P=mod(C2*invKey,26)



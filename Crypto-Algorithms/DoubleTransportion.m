%% ==========  Double Cipher
clc;
clear;
%% =======encrypt
%% ======================================================
plainText='enemyattackstonight';
key=[3 1 4 5 2];
%% ==== Pading======
while (mod(length(plainText),length(key))~=0)
   plainText=[plainText 'x']; 
end;
%% ======Railing
for times=1:2
block=[];
currentIndex=1;
for i=1:(length(plainText)/length(key))
    for j=1:length(key)
    block(i,j)=plainText(currentIndex);
    currentIndex=currentIndex+1;
    end;
end;
char(block)
%% switching columns
temp=block;
for i=1:length(key)
    block(:,i)=temp(:,key(i));
end;
char(block)
%% making Chipher Text
cipherText=[];
    for i=1:size(block,2)
        cipherText=[cipherText block(:,i)'];
    end;
char(cipherText)
plainText=cipherText;
end;

%% =======decrypt
%% ======================================================
cipherText='tiyteaoxhmcseangtktn';
key=[3 1 4 5 2];
for times=1:2
%% ======Railing
block=[];
currentIndex=1;
for j=1:length(key)
    for i=1:(length(cipherText)/length(key))
    block(i,j)=cipherText(currentIndex);
    currentIndex=currentIndex+1;
    end;
end;
char(block)
%% switching columns
temp=block;
for i=1:length(key)
    block(:,key(i))=temp(:,i);
end;
char(block)
%% making Chipher Text
plainText=[];
    for i=1:size(block,1)
        plainText=[plainText block(i,:)];
    end;
char(plainText)
cipherText=plainText;
end;
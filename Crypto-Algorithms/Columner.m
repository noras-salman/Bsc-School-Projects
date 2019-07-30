%% ==========  Columener Cipher
clc;
clear;
%% =======encrypt
%% ======================================================
plainText='wearedescoveredfleeatonce';
key=[5 4 2 6 3 1];
%% ==== Pading======
while (mod(length(plainText),length(key))~=0)
   plainText=[plainText 'x']; 
end;
%% ======Railing
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

%% =======decrypt
%% ======================================================
cipherText='evlnxrofoxeseaxdeecxacdtxweree';
key=[5 4 2 6 3 1];

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
cipherText=[];
    for i=1:size(block,1)
        cipherText=[cipherText block(i,:)];
    end;
char(cipherText)

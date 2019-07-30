%% ==========  Railfence Cipher
clc;
clear;
%% =======encrypt
%% ======================================================
rails=3;
plainText='thisisasecretmessage';
%% ==== Pading======
while (mod(length(plainText),rails)~=0)
   plainText=[plainText 'x']; 
end;
%% ======Railing
block=[];
currentIndex=1;
for j=1:(length(plainText)/3)
    for i=1:rails
    block(i,j)=plainText(currentIndex);
    currentIndex=currentIndex+1;
    end;
end;
char(block)
%% making Chipher Text
cipherText=[];
for i=1:size(block,1)
    for j=1:size(block,2)
        cipherText=[cipherText block(i,j)];
    end;
end;

char(cipherText)

%% =======decrypt
%% ======================================================
rails=3;
cipherText='tsactsghisrmseiseeeax';
%% ======Railing
block=[];
currentIndex=1;
for i=1:rails
    for j=1:(length(cipherText)/3)
    block(i,j)=cipherText(currentIndex);
    currentIndex=currentIndex+1;
    end;
end;
char(block)
%% making Plain Text
plainText=[];

    for i=1:size(block,2)
        plainText=[plainText block(:,i)'];
    end;

char(plainText)



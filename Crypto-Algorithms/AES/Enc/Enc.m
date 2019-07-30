clc; clear;
%% =====gui input
[filename, pathname] = ...
    uigetfile({'*.*';'*.jpg';'*.png';'*.txt';'*.doc';'*.docx'},'File Selector');
fullfilepath=[pathname filename];
protoType=filename(end-2:end);
isImage=0;
if ((strcmp(protoType,'jpg'))||(strcmp(protoType,'png')))
isImage=1;
ASCIIfile=imread(fullfilepath);
s=size(ASCIIfile);
ASCIIfile=[reshape(ASCIIfile(:,:,1),1,s(1)*s(2))';...
           reshape(ASCIIfile(:,:,2),1,s(1)*s(2))';...
           reshape(ASCIIfile(:,:,3),1,s(1)*s(2))'...
           ];
else
fid=fopen(fullfilepath,'r');
ASCIIfile=fread(fid);
end;
key=[];
while ((length(key)==0) || (length(key)>16))
key = inputdlg('Enter File Password:','AES128-Encryption');
end;
key = cell2mat(key);%because the inoutdlg output is a cell
tStart=clock; % start time
h = waitbar(0,'Please wait... (Encrypting)');

% %% ===pdf test
% ASCIIfile=['00' ;'11' ;'22' ;'33' ;'44';'55';'66';'77';'88';'99';'aa';'bb';'cc';'dd';'ee';'ff'];
% ASCIIfile=hex2dec(ASCIIfile);
% ASCIIfile=ASCIIfile';
% ASCIIfile=char(ASCIIfile);
% key=['00';'01';'02';'03';'04';'05';'06';'07';'08';'09';'0a';'0b';'0c';'0d';'0e';'0f'];
% key=hex2dec(key);
% key=key';
% key=char(key);

%% ========    Padding   #1  =====
while mod(length(ASCIIfile),16)~=0
  ASCIIfile=[ASCIIfile; 'x'] ;
end;

%% ======== Making Blocks ========%%
blockedFile=blocking(ASCIIfile);

blockedFile=double(blockedFile);
numofFileBlocks=(size(blockedFile,2)/4);
fprintf('File Divided into: %8.0f Blocks\n',numofFileBlocks);
%% ========    Same for the Key =========== %%

%% ========   Key Padding    =====
while ((mod(length(key),4)~=0) || (length(key)<16))
  key=[key 'x'];      
end;

%% ======== Making key Block ========%%
blockedKey=blocking(key);
%% ========Key Schcedualing ======
KEY=KeyExpand(double(blockedKey));
%% =======================================
cipherBlocks=[];
cipherBlock=[];
cipherKey=[];
%%=== Time Calculating ===
tSubTotal=0;
tShiftTotal=0;
tMixTotal=0;
tAddTotal=0;
%% ====== Round 0-10
for block=1:(numofFileBlocks)
    tCurrent=clock;
    tAll=etime(tCurrent,tStart);
    waitbarmessageupdate={['Please wait... (Encrypting) '...
      ] ,[num2str(block) '/' num2str(numofFileBlocks) ' Blocks   ' num2str(tAll,'%5.2f') ' Sec    '  num2str(100*(block/numofFileBlocks),'%3.2f') ' %']};
    waitbar(block/numofFileBlocks,h,waitbarmessageupdate)
    
    fprintf('===Block: ( %i 0f %i Blocks)=== \n',block,numofFileBlocks);
    cipherBlock=GetBlock(blockedFile,block);
    fprintf('round[0].input  %s \n',dec2hex(cipherBlock)');
    % ====== Add Round Key Round (0) ======
    tAddStart=clock;
    cipherKey=GetBlock(KEY,1);
    fprintf('round[0].k_sch  %s \n',dec2hex(cipherKey)');
    cipherBlock=AddRoundKey(cipherBlock,cipherKey);
    tAddEnd=clock;
    tAddTotal=tAddTotal+etime(tAddEnd,tAddStart);
    for round=1:9
    fprintf('round[%i].start  %s \n',round,dec2hex(cipherBlock)');
   
    % ===== Sub Byte =====
        tSubStart=clock;
        cipherBlock=BlockSubByte(cipherBlock);
        tSubEnd=clock;
        tSubTotal=tSubTotal+etime(tSubEnd,tSubStart);
    fprintf('round[%i].s_box  %s \n',round,dec2hex(cipherBlock)');
    
    % ====== ShitRows ======
    tShiftStart=clock;
    cipherBlock=ShiftRows(cipherBlock);
    tShiftEnd=clock;
    tShiftTotal=tShiftTotal+etime(tShiftEnd,tShiftStart);
    fprintf('round[%i].s_row  %s \n',round,dec2hex(cipherBlock)');
    
    % ====== Mix Columns ======
    tMixStart=clock;
    temp=[];
    for word=1:4
    temp1=MixColumn(Word(cipherBlock,word-1));
    temp=[temp temp1];
    end;
    cipherBlock=temp;
    tMixEnd=clock;
    tMixTotal=tMixTotal+etime(tMixEnd,tMixStart);
    fprintf('round[%i].m_col  %s \n',round,dec2hex(cipherBlock)');
    
    % ====== Add Round Key ======
    tAddStart=clock;
    cipherKey=GetBlock(KEY,round+1);
    fprintf('round[%i].k_sch  %s \n',round,dec2hex(cipherKey)');
    cipherBlock=AddRoundKey(cipherBlock,cipherKey);
    tAddEnd=clock;
    tAddTotal=tAddTotal+etime(tAddEnd,tAddStart);
    end;
    
    fprintf('round[10].start %s \n',dec2hex(cipherBlock)');
    % ===== Sub Byte Round (10)=====
    tSubStart=clock;
    cipherBlock=BlockSubByte(cipherBlock);
    tSubEnd=clock;
    tSubTotal=tSubTotal+etime(tSubEnd,tSubStart);
    fprintf('round[10].s_box %s \n',dec2hex(cipherBlock)');
    
     % ====== ShitRows Round (10) ======
    tShiftStart=clock;
    cipherBlock=ShiftRows(cipherBlock);
    tShiftEnd=clock;
    tShiftTotal=tShiftTotal+etime(tShiftEnd,tShiftStart);
    fprintf('round[10].s_row %s \n',dec2hex(cipherBlock)');
    
    % ====== Add Round Key Round (10) ======
    tAddStart=clock;
    cipherKey=GetBlock(KEY,11);
    fprintf('round[10].k_sch %s \n',dec2hex(cipherKey)');
    cipherBlock=AddRoundKey(cipherBlock,cipherKey);   
    tAddEnd=clock;
    tAddTotal=tAddTotal+etime(tAddEnd,tAddStart);
    
    fprintf('round[10].output %s \n',dec2hex(cipherBlock)');
    cipherBlocks=[cipherBlocks cipherBlock];
end;
cipherBlocks=char(cipherBlocks);
cipherBlocks=reshape(cipherBlocks,1,size(cipherBlocks,1)*size(cipherBlocks,2))';
tEnd=clock;
totalTime=etime(tEnd,tStart);
fprintf([['\n\nTotal Elapsed Time to Encrypt is: ' num2str(totalTime,'%5.2f') ' Seconds '...
    ],['\nTotal SubByte Time: ' num2str(tSubTotal,'%5.2f') ' Seconds '...
    ],['\nTotal ShiftRow Time: ' num2str(tShiftTotal,'%5.2f') ' Seconds '...
    ],['\nTotal MixCol Time: ' num2str(tMixTotal,'%5.2f') ' Seconds '...
    ],['\nTotal AddKey Time: ' num2str(tAddTotal,'%5.2f') ' Seconds \n']]);
close(h);
savefilepath=[pathname 'AES128-' filename];

if isImage==1 %padColumns/reshape/ save
savefilepath=[pathname 'AES128-' filename '.nas'];
cipherBlocks=uint8(cipherBlocks);  
rowNumAfterPadd=length(cipherBlocks)/(s(2)*3);
imShape(:,:,1)=reshape(cipherBlocks(1:s(1)*s(2)),s(1),s(2));
cipherBlocks(1:s(1)*s(2))=[];
imShape(:,:,2)=reshape(cipherBlocks(1:s(1)*s(2)),s(1),s(2));
cipherBlocks(1:s(1)*s(2))=[];
imShape(:,:,3)=reshape(cipherBlocks(1:s(1)*s(2)),s(1),s(2));
imwrite(imShape,savefilepath);
else
fid=fopen(savefilepath,'w');
fwrite(fid,cipherBlocks);
end;
doneMessage={['File Sucsessfuly Encrypted in ' num2str(totalTime,'%5.2f') ' Seconds '...
    ],['Total SubByte Time: ' num2str(tSubTotal,'%5.2f') ' Seconds '...
    ],['Total ShiftRow Time: ' num2str(tShiftTotal,'%5.2f') ' Seconds '...
    ],['Total MixCol Time: ' num2str(tMixTotal,'%5.2f') ' Seconds '...
    ],['Total AddKey Time: ' num2str(tAddTotal,'%5.2f') ' Seconds '...
    ],[],['and SAVED AS: ' savefilepath]};
msgbox(doneMessage,'AES128 Encryption','help');
winopen(savefilepath)

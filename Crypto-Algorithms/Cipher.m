function varargout = Cipher(varargin)
% CIPHER M-file for Cipher.fig
%      CIPHER, by itself, creates a new CIPHER or raises the existing
%      singleton*.
%
%      H = CIPHER returns the handle to a new CIPHER or the handle to
%      the existing singleton*.
%
%      CIPHER('CALLBACK',hObject,eventData,handles,...) calls the local
%      function named CALLBACK in CIPHER.M with the given input arguments.
%
%      CIPHER('Property','Value',...) creates a new CIPHER or raises the
%      existing singleton*.  Starting from the left, property value pairs are
%      applied to the GUI before Cipher_OpeningFcn gets called.  An
%      unrecognized property name or invalid value makes property application
%      stop.  All inputs are passed to Cipher_OpeningFcn via varargin.
%
%      *See GUI Options on GUIDE's Tools menu.  Choose "GUI allows only one
%      instance to run (singleton)".
%
% See also: GUIDE, GUIDATA, GUIHANDLES

% Edit the above text to modify the response to help Cipher

% Last Modified by GUIDE v2.5 05-Nov-2012 22:53:40

% Begin initialization code - DO NOT EDIT
gui_Singleton = 1;
gui_State = struct('gui_Name',       mfilename, ...
                   'gui_Singleton',  gui_Singleton, ...
                   'gui_OpeningFcn', @Cipher_OpeningFcn, ...
                   'gui_OutputFcn',  @Cipher_OutputFcn, ...
                   'gui_LayoutFcn',  [] , ...
                   'gui_Callback',   []);
if nargin && ischar(varargin{1})
    gui_State.gui_Callback = str2func(varargin{1});
end

if nargout
    [varargout{1:nargout}] = gui_mainfcn(gui_State, varargin{:});
else
    gui_mainfcn(gui_State, varargin{:});
end
% End initialization code - DO NOT EDIT


% --- Executes just before Cipher is made visible.
function Cipher_OpeningFcn(hObject, eventdata, handles, varargin)
% This function has no output args, see OutputFcn.
% hObject    handle to figure
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)
% varargin   command line arguments to Cipher (see VARARGIN)

% Choose default command line output for Cipher
handles.output = hObject;

% Update handles structure
guidata(hObject, handles);

% UIWAIT makes Cipher wait for user response (see UIRESUME)
% uiwait(handles.figure1);


% --- Outputs from this function are returned to the command line.
function varargout = Cipher_OutputFcn(hObject, eventdata, handles) 
% varargout  cell array for returning output args (see VARARGOUT);
% hObject    handle to figure
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)

% Get default command line output from handles structure
varargout{1} = handles.output;



function varargout = edit1_Callback(hObject, eventdata, handles)
% hObject    handle to edit1 (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)

% Hints: get(hObject,'String') returns contents of edit1 as text
%        str2double(get(hObject,'String')) returns contents of edit1 as a double
x=get(hObject,'String');
handles.edit1=x;
guidata(hObject,handles);

% --- Executes during object creation, after setting all properties.
function edit1_CreateFcn(hObject, eventdata, handles)
% hObject    handle to edit1 (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    empty - handles not created until after all CreateFcns called

% Hint: edit controls usually have a white background on Windows.
%       See ISPC and COMPUTER.
if ispc && isequal(get(hObject,'BackgroundColor'), get(0,'defaultUicontrolBackgroundColor'))
    set(hObject,'BackgroundColor','white');
end



function varargout = edit2_Callback(hObject, eventdata, handles)
% hObject    handle to edit2 (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)

% Hints: get(hObject,'String') returns contents of edit2 as text
%        str2double(get(hObject,'String')) returns contents of edit2 as a double
x=get(hObject,'String');
handles.edit2=x;
guidata(hObject,handles);

% --- Executes during object creation, after setting all properties.
function edit2_CreateFcn(hObject, eventdata, handles)
% hObject    handle to edit2 (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    empty - handles not created until after all CreateFcns called

% Hint: edit controls usually have a white background on Windows.
%       See ISPC and COMPUTER.
if ispc && isequal(get(hObject,'BackgroundColor'), get(0,'defaultUicontrolBackgroundColor'))
    set(hObject,'BackgroundColor','white');
end



function varargout=edit3_Callback(hObject, eventdata, handles)
% hObject    handle to edit3 (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)

% Hints: get(hObject,'String') returns contents of edit3 as text
%        str2double(get(hObject,'String')) returns contents of edit3 as a double
x=get(hObject,'String');
handles.edit3=x;
guidata(hObject,handles);

% --- Executes during object creation, after setting all properties.
function edit3_CreateFcn(hObject, eventdata, handles)
% hObject    handle to edit3 (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    empty - handles not created until after all CreateFcns called

% Hint: edit controls usually have a white background on Windows.
%       See ISPC and COMPUTER.
if ispc && isequal(get(hObject,'BackgroundColor'), get(0,'defaultUicontrolBackgroundColor'))
    set(hObject,'BackgroundColor','white');
end


% --- Executes on button press in do_Additive.
function do_Additive_Callback(hObject, eventdata, handles)
%======================================================================
clc
key=str2double(handles.edit3)
cipherText=char(handles.edit2)
plainText=char(handles.edit1)
if (get(handles.radiobutton1,'Value') == get(handles.radiobutton1,'Max'))
%===================Encrypt===============

    index=['a':'z'];
cipherAlphabet=['A':'Z'];
cipherText=[];

for i=1:1:length(plainText)
    p=find(index==plainText(i));
    c=mod(((p-1)+key),26);
    cipherText=[cipherText cipherAlphabet(c+1)];
end;
set(handles.edit2,'String',cipherText);
%===================Decrypt===============
else

index=['a':'z'];
cipherAlphabet=['A':'Z'];
plainText=[];
for i=1:1:length(cipherText)
    c=find(cipherAlphabet==cipherText(i));
    p=mod(((c-1)-key),26);
    plainText=[plainText index(p+1)];
end;
set(handles.edit1,'String',plainText);
end

% --- Executes on button press in do_Multiplicated.
function do_Multiplicated_Callback(hObject, eventdata, handles)
% hObject    handle to do_Multiplicated (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)


% --- Executes on button press in do_Affine.
function do_Affine_Callback(hObject, eventdata, handles)
%======================================================================
clc
Mul_key=str2double(handles.edit3)
Add_key=str2double(handles.edit4)
cipherText=char(handles.edit2)
plainText=char(handles.edit1)
if (get(handles.radiobutton1,'Value') == get(handles.radiobutton1,'Max'))
%===================Encrypt===============
%            E(x)=(a*x+b) mod n

index=['a':'z'];
cipherAlphabet=['A':'Z'];

cipherText=[];
for i=1:1:length(plainText)
    p=find(index==plainText(i));
    c=mod((Mul_key*(p-1)+Add_key),26);
    cipherText=[cipherText cipherAlphabet(c+1)];
end;

set(handles.edit2,'String',cipherText);
%===================Decrypt===============
%         D(x)=a^-1 * (x+b^-1) mod n

else

index=['a':'z'];
cipherAlphabet=['A':'Z'];
plainText=[];
for i=1:1:length(cipherText)
    c=find(cipherAlphabet==cipherText(i));
    Mul_Key_MMI=findMMI(Mul_key);
    p=mod(Mul_Key_MMI*((c-1)-Add_key),26);
    plainText=[plainText index(p+1)];
end;
set(handles.edit1,'String',plainText);
end

% --- Executes on button press in do_Playfair.
function do_Playfair_Callback(hObject, eventdata, handles)
%======================================================================
clc
keyword=char(handles.edit3)
cipherText=char(handles.edit2)
plainText=char(handles.edit1)
%% making the polybius square
%initlize
for i=1:1:5
    for j=1:1:5
   polySeq(i,j)='#';
    end;
end;
% replace j with if found in keyword
for i=1:1:length(keyword)
if keyword(i)=='j'
   keyword(i)='i';
end;
end;
%% make square
k=1;
i=1;
j=1;
while k<=length(keyword)
    if j>5
        j=1;i=i+1;
    end;
   if isempty(find(polySeq==keyword(k)))
   polySeq(i,j)=keyword(k);
   k=k+1;j=j+1;
   else
   k=k+1;
   end;
end;
polySeq
%fill with the rest
for alpha='a':'i'
    if j>5
        j=1;i=i+1; % i & j is taken from the  step before
    end;
  if isempty(find(polySeq==alpha))
   polySeq(i,j)=alpha;
   j=j+1;
   end;  
end;
for alpha='k':'z'
    if j>5
        j=1;i=i+1; % i & j is taken from the  step before
    end;
  if isempty(find(polySeq==alpha))
   polySeq(i,j)=alpha;
   j=j+1;
   end;  
end;    

 polySeq   

if (get(handles.radiobutton1,'Value') == get(handles.radiobutton1,'Max'))
%===================Encrypt===============
%making diagrafs
diagrafs=[];
i=1;
inserted=0;%num of force inserted items
while i<=length(plainText)
  if ((i==length(plainText))&& (mod(i+inserted,2)==1)) 
  diagrafs=[diagrafs ;plainText(i) 'x'];%force insert at the end
  i=i+1;
  inserted=inserted+1;
  elseif plainText(i)==plainText(i+1)
 diagrafs=[diagrafs; plainText(i) 'x'];%force insert if repeated
 i=i+1;
 inserted=inserted+1;
else
 diagrafs=[diagrafs ;plainText(i) plainText(i+1)] ;
 i=i+2;
end;  
end;
diagrafs
%==encrypt
[n,m]=size(diagrafs);
cipherText=[];
for i=1:1:n
   [i1,j1]=find(polySeq==diagrafs(i,1));
   [i2,j2]=find(polySeq==diagrafs(i,2));
    
  if i1==i2   
  cipherText=[cipherText polySeq(i1,mod(j1,5)+1) polySeq(i2,mod(j2,5)+1) ];    
  elseif j1==j2
  cipherText=[cipherText polySeq(mod(i1,5)+1,j1) polySeq(mod(i2,5)+1,j2) ];    
  else 
    cipherText=[cipherText polySeq(i1,j2) polySeq(i2,j1) ];    
    
  end; 
    
end; 
cipherText
set(handles.edit2,'String',cipherText);
%===================Decrypt===============
else
%making diagrafs
diagrafs=[];
i=1;
inserted=0;%num of force inserted items
while i<=length(cipherText)
  if ((i==length(cipherText))&& (mod(i+inserted,2)==1)) 
  diagrafs=[diagrafs ;cipherText(i) 'x'];%force insert at the end
  i=i+1;
  inserted=inserted+1;
  elseif cipherText(i)==cipherText(i+1)
 diagrafs=[diagrafs; cipherText(i) 'x'];%force insert if repeated
 i=i+1;
 inserted=inserted+1;
else
 diagrafs=[diagrafs ;cipherText(i) cipherText(i+1)] ;
 i=i+2;
end;  
end;
diagrafs
%==encrypt
[n,m]=size(diagrafs);
plainText=[];
for i=1:1:n
   [i1,j1]=find(polySeq==diagrafs(i,1));
   [i2,j2]=find(polySeq==diagrafs(i,2));
    
  if i1==i2   
      if j1==1
          j1=6; 
      end;
      if j2==1 
          j2=6; 
      end;
  plainText=[plainText polySeq(i1,j1-1) polySeq(i2,j2-1) ];    
  elseif j1==j2
      if i1==1
          i1=6; 
      end;
      if i2==1 
          i2=6; 
      end;
  plainText=[plainText polySeq(mod(i1-1,5),j1) polySeq(mod(i2-1,5),j2) ];    
  else 
    plainText=[plainText polySeq(i1,j2) polySeq(i2,j1) ];    
    
  end; 
    
end; 
cipherText


set(handles.edit1,'String',plainText);
end

% --- Executes on button press in do_Vigener.
function do_Vigener_Callback(hObject, eventdata, handles)
%======================================================================
clc
key=char(handles.edit3)
cipherText=char(handles.edit2)
plainText=char(handles.edit1)
if (get(handles.radiobutton1,'Value') == get(handles.radiobutton1,'Max'))
%===================Encrypt===============

index=['a':'z'];
cipherAlphabet=['A':'Z'];
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
set(handles.edit2,'String',cipherText);
%===================Decrypt===============
else

index=['a':'z'];
cipherAlphabet=['A':'Z'];
plainText=[];
k=0;

for i=1:1:length(cipherText)
    c=find(cipherAlphabet==cipherText(i));
    key1=key(mod(k,length(key))+1);% the Carchater
    key11=find(index==key1)-1;     %the Place of the Carchater
    p=mod(((c-1)-key11),26);
    plainText=[plainText index(p+1)];
    k=k+1;
end;
set(handles.edit1,'String',plainText);
end

% --- Executes on button press in do_AutoKey.
function do_AutoKey_Callback(hObject, eventdata, handles)
%======================================================================
clc
key=str2double(handles.edit3)
cipherText=char(handles.edit2)
plainText=char(handles.edit1)
if (get(handles.radiobutton1,'Value') == get(handles.radiobutton1,'Max'))
%===================Encrypt===============

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
set(handles.edit2,'String',cipherText);
%===================Decrypt===============
else

index=['a':'z'];
cipherAlphabet=['A':'Z'];

plainText=[];
for i=1:1:length(cipherText)
    c=find(cipherAlphabet==cipherText(i));
    p=mod(((c-1)-key),26);
    plainText=[plainText index(p+1)];
    key=p;
end;

set(handles.edit1,'String',plainText);
end


% --- Executes on button press in do_Hill.
function do_Hill_Callback(hObject, eventdata, handles)
% hObject    handle to do_Hill (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)


% --- Executes on button press in pushbutton8.
function pushbutton8_Callback(hObject, eventdata, handles)
% hObject    handle to pushbutton8 (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)


% --- Executes on button press in pushbutton9.
function pushbutton9_Callback(hObject, eventdata, handles)
% hObject    handle to pushbutton9 (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)


% --- Executes on button press in radiobutton1.
function radiobutton1_Callback(hObject, eventdata, handles)
% hObject    handle to radiobutton1 (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)

% Hint: get(hObject,'Value') returns toggle state of radiobutton1


% --- Executes on button press in radiobutton2.
function radiobutton2_Callback(hObject, eventdata, handles)
% hObject    handle to radiobutton2 (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)

% Hint: get(hObject,'Value') returns toggle state of radiobutton2



function varargout = edit4_Callback(hObject, eventdata, handles)
% hObject    handle to edit4 (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)

% Hints: get(hObject,'String') returns contents of edit4 as text
%        str2double(get(hObject,'String')) returns contents of edit4 as a double
x=get(hObject,'String');
handles.edit4=x;
guidata(hObject,handles);

% --- Executes during object creation, after setting all properties.
function edit4_CreateFcn(hObject, eventdata, handles)
% hObject    handle to edit4 (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    empty - handles not created until after all CreateFcns called

% Hint: edit controls usually have a white background on Windows.
%       See ISPC and COMPUTER.
if ispc && isequal(get(hObject,'BackgroundColor'), get(0,'defaultUicontrolBackgroundColor'))
    set(hObject,'BackgroundColor','white');
end

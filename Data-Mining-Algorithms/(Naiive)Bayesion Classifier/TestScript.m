clc;
clear;


%% Test Naiive Classification(Bayesion)
fprintf(['Testing (Naiive)Bayesion Classifier on set '...
'(youth medium yes fair) \n']);
DataSet; % Loading Data Set
Naiive(D,y,[youth medium yes fair]);

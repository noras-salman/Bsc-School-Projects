
%% Initialization
clear ; close all; clc

%% Setup the parameters you will use for this exercise
input_layer_size  = 8;  %  8 features
hidden_layer_size = 3;   % 3 hidden units
num_labels = 1;          % 1 (win ,lose ,tie)  

%x1 num of empty
%x2 num of full
%x3 num of 1 X in  row
%x4 num of 1 O in  row
%x5 num of 2 Xs in a row
%x6 num of 2 Os in a row
%x7 num of 3 Xs in a row
%x8 num of 3 Os in a row

%% =========== Loading  Data =============


% Load Training Data
fprintf('Loading Data ...\n');
board(1:3,1:3)=0.5; % (0.5 for empty) (1 for X) (-1 for O)

X =getFeaturesFromBoard(board);
y=0;
m = size(X, 1);



%% ================  Initializing Pameters ================

fprintf('\nInitializing Neural Network Parameters ...\n')

initial_Theta1 = randInitializeWeights(input_layer_size, hidden_layer_size);
initial_Theta2 = randInitializeWeights(hidden_layer_size, num_labels);

% Unroll parameters
initial_nn_params = [initial_Theta1(:) ; initial_Theta2(:)];
fprintf('Program paused. Press enter to continue.\n');
pause;
%% =================== Training NN ===================

fprintf('\nTraining Neural Network... \n')

%  After you have completed the assignment, change the MaxIter to a larger
%  value to see how more training helps.
options = optimset('MaxIter', 500);

%  You should also try different values of lambda
lambda = 1  ;

% Create "short hand" for the cost function to be minimized
costFunction = @(p) ModifiednnCostFunction(p, ...
                                   input_layer_size, ...
                                   hidden_layer_size, ...
                                   num_labels, lambda);

% Now, costFunction is a function that takes in only one argument (the
% neural network parameters)
[nn_params, cost] = fmincg(costFunction, initial_nn_params, options);

% Obtain Theta1 and Theta2 back from nn_params
Theta1 = reshape(nn_params(1:hidden_layer_size * (input_layer_size + 1)), ...
                 hidden_layer_size, (input_layer_size + 1));

Theta2 = reshape(nn_params((1 + (hidden_layer_size * (input_layer_size + 1))):end), ...
                 num_labels, (hidden_layer_size + 1));

fprintf('Program paused. Press enter to continue.\n');
pause;



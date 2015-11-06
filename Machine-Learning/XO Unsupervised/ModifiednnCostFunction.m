function [J grad] = ModifiednnCostFunction(nn_params, ...
                                   input_layer_size, ...
                                   hidden_layer_size, ...
                                   num_labels, ...
                                    lambda)
%NNCOSTFUNCTION Implements the neural network cost function for a two layer
%neural network which performs classification
%   [J grad] = NNCOSTFUNCTON(nn_params, hidden_layer_size, num_labels, ...
%   X, y, lambda) computes the cost and gradient of the neural network. The
%   parameters for the neural network are "unrolled" into the vector
%   nn_params and need to be converted back into the weight matrices. 
% 
%   The returned parameter grad should be a "unrolled" vector of the
%   partial derivatives of the neural network.
%

% Reshape nn_params back into the parameters Theta1 and Theta2, the weight matrices
% for our 2 layer neural network
Theta1 = reshape(nn_params(1:hidden_layer_size * (input_layer_size + 1)), ...
                 hidden_layer_size, (input_layer_size + 1));
Theta2 = reshape(nn_params((1 + (hidden_layer_size * (input_layer_size + 1))):end), ...
                 num_labels, (hidden_layer_size + 1));

% Setup some useful variables
%% m = size(X, 1);
         
% You need to return the following variables correctly 
J = 0;
Theta1_grad = zeros(size(Theta1));
Theta2_grad = zeros(size(Theta2));

% ====================== YOUR CODE HERE ======================
% Instructions: You should complete the code by working through the
%               following parts.
%
% Part 1: Feedforward the neural network and return the cost in the
%         variable J. After implementing Part 1, you can verify that your
%         cost function computation is correct by verifying the cost
%         computed in ex4.m
%
% Part 2: Implement the backpropagation algorithm to compute the gradients
%         Theta1_grad and Theta2_grad. You should return the partial derivatives of
%         the cost function with respect to Theta1 and Theta2 in Theta1_grad and
%         Theta2_grad, respectively. After implementing Part 2, you can check
%         that your implementation is correct by running checkNNGradients
%
%         Note: The vector y passed into the function is a vector of labels
%               containing values from 1..K. You need to map this vector into a 
%               binary vector of 1's and 0's to be used with the neural network
%               cost function.
%
%         Hint: We recommend implementing backpropagation using a for-loop
%               over the training examples if you are implementing it for the 
%               first time.
%
% Part 3: Implement regularization with the cost function and gradients.
%
%         Hint: You can implement this around the code for
%               backpropagation. That is, you can compute the gradients for
%               the regularization separately and then add them to Theta1_grad
%               and Theta2_grad from Part 2.
%
board(1:3,1:3)=0.5;
avalablePickups=find(board==0.5);
X =getFeaturesFromBoard(board);
y=[-1];

%                tie                                  X wins                           O wins
while (( (length(find(X(:,1)==0)))==0) && ((length(find(X(:,7)==1)))==0) && ((length(find(X(:,8)==1)))==0) )
board=reshape(board,3,3);
H=[];
newX=[];
Y=[];
temp=board;
for i=1:length(avalablePickups) %Play X
board=reshape(board,1,9);
board(avalablePickups(i))=1;
board=reshape(board,3,3);
newX=[newX ;getFeaturesFromBoard(board)];
% Forward propagation
m = size(X, 1);
a1 = [1 newX(end,:)];
z2 = a1*Theta1';
a2 = sigmoid(z2);
a2 = [1 a2];
z3 = a2*Theta2';
a3 = sigmoid(z3);
H = [H;a3];
board=temp;
end;
[h,ii]=max(H);
board=reshape(board,1,9);
board(avalablePickups(ii))=1;
X(end+1,:)=newX(ii,:);
avalablePickups(ii)=[];
temp=board;


if ((length(find(X(:,7)==1)))~=0)
y(end+1,1)=1; 
elseif ( (length(find(X(:,1)==0)))~=0)
y(end+1,1)=0.5;
elseif ((length(find(X(:,8)==1)))~=0) 
y(end+1,1)=0;  
else
temp=board;
for i=1:length(avalablePickups) %Play O
board=reshape(board,1,9);
board(avalablePickups(i))=-1;
board=reshape(board,3,3);
m = size(X, 1);
% Forward propagation
a1 = [1 X(end,:)];
z2 = a1*Theta1';
a2 = sigmoid(z2);
a2 = [1 a2];
z3 = a2*Theta2';
a3 = sigmoid(z3);
Y = [Y;a3];
board=temp;
end;
[k,ii]=min(Y);
board=reshape(board,1,9);
board(avalablePickups(ii))=-1;
y(end+1,1)=k;
avalablePickups(ii)=[];
Y=[];
end;
end;
board=reshape(board,3,3);
m = size(X, 1);

%%   End Of Making the data set (One Game is ended)
a1 = [ones(m, 1) X];
z2 = a1*Theta1';
a2 = sigmoid(z2);
a2 = [ones(m,1) a2];
z3 = a2*Theta2';
a3 = sigmoid(z3);
H = a3;
%regularization 
out=zeros(m,num_labels);
for c=1:num_labels
    out(:,c)=(y==c);
end;
Y=out;
J = 1/m * sum(sum(-Y.*log(H)-(1-Y).*log(1-H)));
th1 = Theta1(:,2:end);
th2 = Theta2(:,2:end);
J = J+lambda*(sum(sum(th1.*th1))+sum(sum(th2.*th2)))/(2*m);


%deltas
delta3=a3-Y;
delta2=delta3*Theta2(:,2:end).*sigmoidGradient(z2);

D2 = delta3' * a2;
D1 = delta2' * a1;

REGtheta2 = lambda * [zeros(size(Theta2,1),1), Theta2(:,2:end)];
REGtheta1 = lambda * [zeros(size(Theta1,1),1), Theta1(:,2:end)];
Theta2_grad = (D2 + REGtheta2) ./ m;
Theta1_grad = (D1 + REGtheta1) ./ m;
% -------------------------------------------------------------

% =========================================================================

% Unroll gradients
grad = [Theta1_grad(:) ; Theta2_grad(:)];

%pred = predict(Theta1, Theta2, X);
%fprintf('\nTraining Set Accuracy: %f\n', mean(double(pred == y)) * 100);
%fprintf('========================= \n');


end

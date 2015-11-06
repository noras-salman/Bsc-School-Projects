function [ nc ] = LablasCorrection( D,X,y,featureIndex,yes_no )

nc=length(find(and(D(:,featureIndex)==X(featureIndex),y==yes_no)));
if nc==0
fprintf('A feature Needs Lablas Correction\n');
features=unique(y);
for i=1:length(unique(features))
D=[D; X];
y=[y; features(i)];
end;
nc=length(find(and(D(:,featureIndex)==X(featureIndex),y==yes_no)));
end;

end


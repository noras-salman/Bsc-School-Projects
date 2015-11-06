function [ probability ] = P( set,featureIndex,type )

m=size(set,1);
probability=length(find(set(:,featureIndex)==type))/m;

end


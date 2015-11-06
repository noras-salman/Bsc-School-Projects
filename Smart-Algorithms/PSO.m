%% Swarm Pre..Optimization SPO
clc;clear all;
    
%% function objectif
f = inline('15*x-x^2','x');
lim_inf=0;
lim_sup=15;
    
%% Initialization Parameters
iteration=20;
swarm_size = 1;
c1=2.0; c2=2.0;
w=0.5;
    
%% Initial swarm position
for k=1:swarm_size
   swarm(k,1)=rand*(lim_sup-lim_inf);
   Pbest(k,1)=swarm(k,1);
   Evaluation(k)=f(Pbest(k,1));
   v(k,1)=0;
end
   swarm 
   Pbest
  Evaluation  
for i=1:iteration
    [fbest,best]=max(Evaluation);
    gbest=swarm(best,1);
    
    for k=1:swarm_size
       v(k,1)=0.5*v(k,1)+c1*rand*(Pbest(k,1)-swarm(k,1))+c2*rand*(gbest-swarm(k,1));
    
    
    new_pos=swarm(k,1)+v(k,1);
    
    if new_pos>lim_inf & new_pos<lim_sup
        swarm(k,1)=new_pos;
        if f(new_pos)>f(Pbest(k,1))
            Pbest(k,1)=new_pos;
            Evaluation(k)=f(Pbest(k,1));
        end
    end
    end
       swarm 
   Pbest
  Evaluation  
  gbest
end
gbest
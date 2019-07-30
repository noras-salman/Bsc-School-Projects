function [ t1 ] = eqlidian( r1,r2 )
%eqlidian equation
t1=0;
t2=1;
x=r1;
while (r2>0)
    q=floor(r1/r2);
    r=r1-q*r2;
    r1=r2;
    r2=r;
    t=t1-q*t2;
    t1=t2;
    t2=t;
end;
t1=mod(t1,x);

end


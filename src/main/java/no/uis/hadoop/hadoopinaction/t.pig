 log  = LOAD 'excite-small.log' AS (user, time, query);
        grpd = GROUP log BY user;
        cntd = FOREACH grpd GENERATE group, COUNT(log);
        DUMP cntd;

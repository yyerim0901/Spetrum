```
**cmd창 켜기**
**workbench에서도 가능할테지만 권한오류 해결해야 될 듯**

cd C:\Program Files\MySQL\MySQL Server 8.0\bin

mysql -h k5b101.p.ssafy.io -P 3306 --local_infile=1 -u ssafy -p
mysql -h 124.56.52.142 -P 3306 --local_infile=1 -u threekim -p
password : {application.properties에 있는 pw입력}

mysql> set global local_infile=1;
mysql> LOAD DATA LOCAL INFILE "c:/SSAFY/animalsInfo2.csv" INTO TABLE spetrum.pet CHARACTER SET euckr FIELDS TERMINATED BY ",";
```


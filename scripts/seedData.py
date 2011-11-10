from datetime import * 
from time import *
import os

def daterange(start_date, end_date):
    for n in range((end_date - start_date).days):
        yield start_date + timedelta(n)

for single_date in daterange(date(2008,01,01), date(2008,01,03)):
    year = strftime("%Y", single_date.timetuple())
    day  = strftime("%d", single_date.timetuple())
    month = strftime("%b", single_date.timetuple()).upper()
    fileToDownload = "http://www.nseindia.com/content/historical/DERIVATIVES/{year}/{month}/fo{day}{month}{year}bhav.csv.zip".format(year=year,day=day,month=month)
    os.system('curl --user-agent "Mozilla/4.0 (compatible; MSIE 5.01; Windows NT 5.0)" {0} > temp.zip'.format(fileToDownload))
    os.system('unzip -d bhavs temp.zip && rm -rf temp.zip')

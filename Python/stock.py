from bs4 import BeautifulSoup
import urllib.request
import time
import codecs

if __name__ == '__main__':
    with codecs.open('stocks.csv', 'r', encoding='utf-8', errors='ignore') as f:
        stocks = f.readlines()[1:]

    interesting = []

    for stock in stocks:
        columns = stock.split(",")
        name, code = columns[0], columns[1]

        # Solve 403 forbidden error
        headers = {'User-Agent':'Chrome/66.0.3359.181'}

        url = "http://paxnet.asiae.co.kr/stock/home?abbrSymbol=" + code + "&wlog_rpax=Search_rank"
        request = urllib.request.Request(url, headers=headers)
        response = urllib.request.urlopen(request)
        content = response.read().decode("utf-8", 'ignore')

        soup = BeautifulSoup(content, 'html5lib')
        print(soup)

        soup_today = soup.select(".contents .gnbHtml .open .box .pan-cont .num .color-red")
        print(soup_today)
        soup_rate = soup.select(".contents .gnbHtml .open .box .pan-cont .num .rise")
        print(soup_rate)
        today = soup_today[0].text
        rate = soup_rate[0].text

        fRate = float(rate[:-1])
        if abs(fRate) > 15:
            interesting.append( (name, code, today, rate, fRate) )
        
        print(name, code, today, rate, fRate)
        time.sleep(1)

    print("today's interesting stocks : ")
    for i in interesting:
        print(i)
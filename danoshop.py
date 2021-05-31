from selenium import webdriver
from bs4 import BeautifulSoup
import time
import re
from webdriver_manager.chrome import ChromeDriverManager
# from pymongo import MongoClient
import pymysql

# conn = pymysql.connect(host="database-1.cnxzxzdb95qp.ap-northeast-2.rds.amazonaws.com", user='admin', password='Danoshop', db="DanoShop", charset="utf8")
conn = pymysql.connect(host="localhost", user='user1',
                       password='password!', db='pythonDB', charset="utf8")
cur = conn.cursor()

driver = webdriver.Chrome(ChromeDriverManager().install())
driver.get('https://danoshop.net/category?cate_no=0')
time.sleep(7)

sql = "CREATE TABLE IF NOT EXISTS product (id int(10) NOT NULL AUTO_INCREMENT PRIMARY KEY, image_url varchar(255), product_name varchar(255), price varchar(255), isTrending boolean, isNew boolean, isDano boolean, isBestDeal boolean, isFree boolean)"
cur.execute(sql)
conn.commit()

html = driver.page_source

soup = BeautifulSoup(html, 'html.parser')
items = soup.findAll("li", {"class": re.compile("prd-list")})

for item in items:
    image_tag = item.find("img")
    image_url = image_tag["data-src"]
    product_list = item.findAll("span")
    product_name = product_list[0].text
    price = product_list[1].text
    isTrending = False
    isNew = False
    isDano = False
    isBestDeal = False
    isFree = False
    if len(price) < 4:
        price = product_list[3].text
    sql = "INSERT INTO product(image_url, product_name, price, isTrending, isNew, isDano, isBestDeal, isFree) VALUES('" + image_url + \
        "','" + product_name + "','" + price + \
        "',isTrending ,isNew ,isDano,isBestDeal,isFree)"
    cur.execute(sql)
conn.commit()
conn.close()

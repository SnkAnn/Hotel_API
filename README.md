Backeng часть RESTful API приложение для работы с отелями
-
**Используемые технологии:**
<li>Maven</li>
<li>Java 17+</li>
<li>Spring Boot</li>
<li>Spring JPA</li>
<li>Liquibase</li>
<li>Lombok</li>

###
**БД:**
<li>H2</li>

Для смены H2 на другую базу данных надо изменить application.yml файл, где заменить блок datasource на соответсвующий вашей БД.
Все измениея и настройки БД производятся через liquibase.
###

**Реализованные методы:**

1)	
	GET /hotels - получение списка всех отелей с их краткой информацией
###
	Пример ответа:
		[
			{
				"id": 1,
				"name": "DoubleTree by Hilton Minsk",
				"description": "The DoubleTree by Hilton Hotel Minsk offers 193 luxurious rooms in the Belorussian capital and stunning views of Minsk city from the hotel's 20th floor ..."
				"address": "9 Pobediteley Avenue, Minsk, 220004, Belarus",
				"phone": "+375 17 309-80-00"
			}
		]
2)	
	GET /holets/{id} - получение расширенной информации по конктретному отелю
###
	Пример ответа:
		{
			"id": 1,
			"name": "DoubleTree by Hilton Minsk",
			"brand" "Hilton",
			"address": 
				{
					"houseNumber": 9
					"street": "Pobediteley Avenue",
					"city": "Minsk",
					"county": "Belarus",
					"postCode": "220004"
				}
			"contacts": 
				{
					"phone": "+375 17 309-80-00",
					"email": doubletreeminsk.info@hilton.com
				},
			"arrivalTime:
				{
					"checkIn": "14:00",
					"checkOut": "12:00"
				},
			"amenities": 
				[
					"Free parking",
					"Free WiFi",
					"Non-smoking rooms",
					"Concierge",
					"On-site restaurant",
					"Fitness center",
					"Pet-friendly rooms",
					"Room service",
					"Business center",
					"Meeting rooms"
				]
		}
3)
	GET /search - поиск получение списка всех отелей с их краткой информацией по следующим параметрам: name, brand, city, county, amenities. Например: /search?city=minsk
###
	Пример ответа:
		см. GET /hotels
4)
	POST /hotels - создание нового отеля
###
	Пример запроса:
		{
			"name": "DoubleTree by Hilton Minsk",
			"description": "The DoubleTree by Hilton Hotel Minsk offers 193 luxurious rooms in the Belorussian capital and stunning views of Minsk city from the hotel's 20th floor ...", - (optional)
			"brand" "Hilton",
			"address": 
				{
					"houseNumber": 9
					"street": "Pobediteley Avenue",
					"city": "Minsk",
					"county": "Belarus",
					"postCode": "220004"
				}
			"contacts": 
				{
					"phone": "+375 17 309-80-00",
					"email": doubletreeminsk.info@hilton.com
				},
			"arrivalTime:
				{
					"checkIn": "14:00",
					"checkOut": "12:00" - (optional)
				}
		}
  ###
	Пример ответа:
		{
			"id": 1,
			"name": "DoubleTree by Hilton Minsk",
			"description": "The DoubleTree by Hilton Hotel Minsk offers 193 luxurious rooms in the Belorussian capital and stunning views of Minsk city from the hotel's 20th floor ...",
			"address": "9 Pobediteley Avenue, Minsk, 220004, Belarus",
			"phone": "+375 17 309-80-00"
		}
5)
	POST /hotels/{id}/amenities - добавление списка amenities к отелю
  ###
	Пример запроса:
		[
			"Free parking",
			"Free WiFi",
			"Non-smoking rooms",
			"Concierge",
			"On-site restaurant",
			"Fitness center",
			"Pet-friendly rooms",
			"Room service",
			"Business center",
			"Meeting rooms"
		]
6)		
	GET /histogram/{param} - получение колличества отелей сгруппированных по каждому значению указанного параметра. Параметр: brand, city, county, amenities.
  ###
	Например: /histogram/city должен вернуть:
		{
			"Minsk": 1,
			"Moskow: 2,
			"Mogilev: 0,
			и тд.
		}
	а /histogram/amenities должен вернуть:
		{
			"Free parking": 1,
			"Free WiFi: 20,
			"Non-smoking rooms": 5,
			"Fitness center": 0,
			и тд.
		}
 

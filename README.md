Dependencies:

   - Все версии для библиотек описаны в файле gradle.properties

1. Миссия:
Демонстрация технической квалификации для трудоустройства в компанию Electrolux на позицию android developer.


2. Функционал:

2.1. Общее описание:
Мобильное приложение, которое демонстрирует на экране пользователя список изображений с возможностью сохранения в память телефона выбранного изображения.

2.2. Подробный перечень пользовательских функций: 
- Карусель изображений,
- Поиск изображений по ключевому слову,
- Возможность загрузить изображение на телефон для доступа через стандартное хранилище изображений (галерея).


3. Особенности:

3.1. Стек технологий:

3.1.1.Общие:
Kotlin Multiplatform Mobile, network access by Ktor, testing

3.1.2. андроид:
Jetpack compose

3.1.3. iOS:
Реализация не предусматривается в рамках текущего задания.
3.1.4. Серверные:
Реализация не предусматривается в рамках текущего задания.

4.Реализация:

4.1. Список экранов:

4.1.1. Стартовая карусель изображений (Lazy Column).


4.2. Связи экранов:

4.2.1. При старте приложения пользователь попадает на экран карусели изображений 4.1.1

4.2.2. При нажатии на выбранное изображение пользователь получает возможность загрузить изображение в стандартное хранилище пользовательских изображений телефона. 


4.3. Требования к экранам:

4.3.1. Карусель изображений должна кешировать изображения.

4.3.2. Изображения должны показываться пользователю по мере загрузки из сети.

4.3.3. Изображения должны иметь значения по умолчанию для демонстрации до завершения загрузки из сети.

4.3.4. Карусель должна иметь поле ввода текста для загрузки изображений по произвольному ключевому слову.

4.4. Архитектура:

4.4.1. Общая архитектура:

4.4.2. Архитектура мобильного приложения:

  4.4.2.1. Используем стандартную clean архитектуру;

  4.4.2.2. На уровне представления используем составной паттерн MVVM;

  4.4.2.3. Сущность 'ViewModel' обращается к 'Repository', который инкапсулирует всю логику работы с Data layer(Database,network и др.).

5. Технические требования:

5.1. Поддержка android os 7.0 и выше,

5.2. Поддержка iOS - regular framework, 

5.3. API для загрузки изображений - https://www.flickr.com/services/api/flickr.photos.search.html,

5.4. Автоматическое тестирование.

6. Нетехнические требования:

6.1. Плавность работы UI.

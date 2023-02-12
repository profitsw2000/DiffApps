Имя разработчика: Уланов Савелий

Дата создания проекта: 17.01.2023

Название приложения: DiffApps

Описание: учебное приложение для выполнения домашних заданий к урокам курса "Подготовка к собеседованию Android-разработчика". Функционал веток может быть никак не связан друг с другом. Ветка мастер содержит только базовый коммит, новые ветки создаются только от неё.

Функциональность:

17.01.2023, ветка lesson_1 - приложение с геолокацией. При старте приложения появляется диалоговое окно с запросом разрешения на определение местоположения. Если разрешение получено, определяется местоположение пользователя и отображается на карте специальной меткой. Отображение текущего местоположения также доступно при нажатии на кнопку геолокации в правом верхнем углу. На карте можно поставить маркеры, а при выборе соответствующего пункта меню посмотреть и отредактировать на отдельном экране список маркеров, добавить маркеру название и аннотацию.

22.01.2023, ветка lesson_2 - приложение с фильмами. При старте приложения происходит загрузка списка сериалов с рейтингом от 8 до 10 за 2022 год. Список поддерживает пагинацию, при достижении конца списка при пролистывании автоматически подгружаются новые данные. При нажатии на элемент списка (выборе фильма(сериала)) происходит переход на экран с описанием выбранного фильма(сериала).

22.01.2023, ветка lesson_3 - приложение с анимированным макетом машины как в приложении Я.Гоу или СитиМобил. В исходном состоянии, при загрузке приложения, макет авто находится в левом верхнем углу(книжная ориентация телефона). По тапу на машине она начинает движение в противоположный угол по синусоидальной траектории. Достигнув правого нижнего угла(книжная ориентация телефона) авто останавливается. При повторном нажатии на макет машины происходит движение на исходную позицию по той же траектории - в левый верхний угол. Архитектура приложения - MVVM, использовались библиотеки Koin, Kotlin Coroutines, LiveData.

31.01.2023, ветка lesson_4 - приложение с расписанием занятий по заданному шаблону с дизайном. Приложение с нижней навигацией (4 таба - Home, Classes, Notes, Favorites).
Во вкладке Home имеется таймер оповещения до начала экзаменов (дата экзамена задана в коде), горизонтальный список с уроками на день и горизонтальный список домашних заданий с кратким описанием ДЗ и временем до сдачи ДЗ (все данные для списков- из фейкового источника в коде, на каждый день одно и то же расписание). При открытии приложения список уроков автоматически скроллиться на ближайший или текущий урок.
Во вкладке Classes - расписание уроков в хронологическом порядке (черные карточки) и информация о дополнительных занятиях (зеленая карточка). При тапе на карточку, у которой есть шеврон Open In справа открывается Skype (если Skype не установлен на телефоне появляется тоаст-сообщение об ошибке).
Остальные вкладки пустые.

04.02.2023, ветка lesson_5 - приложение с бесконечным списком популярных постов с Reddit. В приложении один экран, который отображает список популярных постов на Реддите. По мере того, как пользователь пролистывает экран, происходит автоматическая подгрузка данных. При написании приложения использовалась библиотека Paging 3.0. Кэширование данных не происходит.

12.02.2023, ветка lesson_6 - приложение, которое хранит и отображает давление и пульс пользователя. В приложении один экран с результатами измерения давления и пульса, разделённые по времени. При нажатии на FAB открывается диалоговое окно для ввода результатов измерения. Данные хранятся в облачном хранилище Firestore.
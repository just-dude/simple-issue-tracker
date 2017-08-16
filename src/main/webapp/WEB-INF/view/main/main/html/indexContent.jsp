<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<s:url var="addUserUrl" namespace="/admin/users" action="add-user-input">
    <s:param name="locale" value="%{#request.locale}"/>
</s:url>

<p id="welcome_text" class="text">
    Добро пожаловать на официальный сайт забайкальского государственного университета!
</p>
    <div>                 
        <div class="tile">
            <div class="tile_wraper">
                <a href="#" class="g_t_sen"><div class="tile_main">
                        <img src="/resources/main-theme/img/tiles/entrant.jpg" width="180" height="146">
                        <div class="inf_for">Поступающему</div>
                </div></a>
                <div class="tile_sub">
                        <div class="head_sub" >Поступающему</div>
                        <div class="text_sub"></br></br></br>Всё для абитуриентов</br></br></br></div>
                        <a href="#" class="g_t"><div class="to_section" >
                                <img class="g_t_img" src="/resources/main-theme/img/to_sect.png" width="12" height="10"/>
                                <span class="g_t">Перейти к разделу</span>
                        </div></a>
                </div>
            </div>
        </div>
        <div class="tile">
                <div class="tile_wraper">
                        <a href="#" class="g_t_sen"><div class="tile_main">
                                <img src="/resources/main-theme/img/tiles/student.jpg" width="180" height="146">
                                <div class="inf_for">Студенту</div>
                        </div></a>
                        <div class="tile_sub">
                                <div class="head_sub" >Студенту</div>
                                <div class="text_sub">Образовательные программы, расписание занятий, нормативные документы</div>
                                <a href="#" class="g_t_sen"><div class="to_section" >
                                        <img class="g_t_img" src="/resources/main-theme/img/to_sect.png" width="12" height="10"/>
                                        <span class="g_t">Перейти к разделу</span>
                                </div></a>
                        </div>
                </div>
        </div>
        <div class="tile">
                <div class="tile_wraper">
                        <a href="#" class="g_t_sen"><div class="tile_main">
                                <img src="/resources/main-theme/img/tiles/postgraduate.jpg" width="180" height="146">
                                <div class="inf_for">Аспиранту</div>
                        </div></a>
                        <div class="tile_sub">
                                <div class="head_sub" >Аспиранту</div>
                                <div class="text_sub"></br>Аспирантура и докторантура ЗабГУ: общая информация, полезные ссылки</br></br></div>
                                <a href="#" class="g_t_sen"><div class="to_section" >
                                        <img class="g_t_img" src="/resources/main-theme/img/to_sect.png" width="12" height="10"/>
                                        <span class="g_t">Перейти к разделу</span>
                                </div></a>
                        </div>
                </div>
        </div>
        <div class="tile">
                <div class="tile_wraper">
                                <a href="#" class="g_t_sen"><div class="tile_main">
                                        <img src="/resources/main-theme/img/tiles/worker.jpg" width="180" height="146">
                                        <div class="inf_for">Сотруднику</div>
                                </div></a>
                                <div class="tile_sub">
                                        <div class="head_sub" >Сотруднику</div>
                                        <div class="text_sub"></br></br>Нормативные документы, полезные ссылки</br></br></div>
                                        <a href="#" class="g_t_sen"><div class="to_section" >
                                                <img class="g_t_img" src="/resources/main-theme/img/to_sect.png" width="12" height="10"/>
                                                <span class="g_t">Перейти к разделу</span>
                                        </div></a>
                                </div>
                        </div>
        </div>
        <div class="tile">
                <div class="tile_wraper">
                        <a href="#" class="g_t_sen"><div class="tile_main">
                                <img src="/resources/main-theme/img/tiles/university.jpg" width="180" height="146">
                                <div class="inf_for">Об университете</div>
                        </div></a>
                        <div class="tile_sub">
                                <div class="head_sub" >Об университете</div>
                                <div class="text_sub">История университета, информация о ректорате, факультетах, отделах и службах</div>
                                <a href="#" class="g_t_sen"><div class="to_section" >
                                        <img class="g_t_img" src="/resources/main-theme/img/to_sect.png" width="12" height="10"/>
                                        <span class="g_t">Перейти к разделу</span>
                                </div></a>
                        </div>
                </div>
        </div>
        <div class="tile">
                <div class="tile_wraper">
                        <a href="#" class="g_t_sen"><div class="tile_main">
                                <img src="/resources/main-theme/img/tiles/sience.jpg" width="180" height="146">
                                <div class="inf_for">Наука</div>
                        </div></a>
                        <div class="tile_sub">
                                <div class="head_sub" >Наука</div>
                                <div class="text_sub"></br>Научные направления университета, мероприятия, научно-исследовательская работа студентов</div>
                                <a href="#" class="g_t"><div class="to_section" >
                                        <img class="g_t_img" src="/resources/main-theme/img/to_sect.png" width="12" height="10"/>
                                        <span class="g_t">Перейти к разделу</span>
                                </div></a>
                        </div>
                </div>
        </div>
        <div class="tile">
                <div class="tile_wraper">
                        <a href="#" class="g_t_sen"><div class="tile_main">
                                <img src="/resources/main-theme/img/tiles/press_hub.jpg" width="180" height="146">
                                <div class="inf_for">Медиа</div>
                        </div></a>
                        <div class="tile_sub">
                                <div class="head_sub" >Медиа</div>
                                <div class="text_sub"><br/>Фотогалерея, видео, аудио-материалы о жизни университета, СМИ о нас<br/><br/></div>
                                <a href="#" class="g_t"><div class="to_section" >
                                        <img class="g_t_img" src="/resources/main-theme/img/to_sect.png" width="12" height="10"/>
                                        <span class="g_t">Перейти к разделу</span>
                                </div></a>
                        </div>
                </div>
        </div>
        <div class="tile">
                <div class="tile_wraper">
                        <a href="#" class="g_t_sen"><div class="tile_main">
                                <img src="/resources/main-theme/img/tiles/library.jpg" width="180" height="146">
                                <div class="inf_for" style="font-size: 10px;">Заочное, дополнительное обучение</div>
                        </div></a>
                        <div class="tile_sub">
                                <div class="head_sub" >Заочное, дополнительное обучение</div>
                                <div class="text_sub"><br/><br/>Информация для студентов заочного факультета<br/><br/></div>
                                <a href="#" class="g_t"><div class="to_section" >
                                        <img class="g_t_img" src="/resources/main-theme/img/to_sect.png" width="12" height="10"/>
                                        <span class="g_t">Перейти к разделу</span>
                                </div></a>
                        </div>
                </div>
        </div>
        <div class="tile">
                <div class="tile_wraper">
                        <a href="#" class="g_t_sen"><div class="tile_main">
                                <img src="/resources/main-theme/img/tiles/doc.jpg" width="180" height="146">
                                <div class="inf_for">Документы, лицензия</div>
                        </div></a>
                        <div class="tile_sub">
                                <div class="head_sub" >Документы, лицензии</div>
                                <div class="text_sub"><br/>Свидетельство о государственной аккредитации, лицензия<br/><br/></div>
                                <a href="#" class="g_t"><div class="to_section" >
                                        <img class="g_t_img" src="/resources/main-theme/img/to_sect.png" width="12" height="10"/>
                                        <span class="g_t">Перейти к разделу</span>
                                </div></a>
                        </div>
                </div>
        </div>
        <div class="tile">
                <div class="tile_wraper">
                        <a href="#" class="g_t_sen"><div class="tile_main">
                                <img src="/resources/main-theme/img/tiles/library_cat.jpg" width="180" height="146">
                                <div class="inf_for">Научная библиотека</div>
                        </div></a>
                        <div class="tile_sub">
                                <div class="head_sub" >Научная библиотека</div>
                                <div class="text_sub"><br/><br/> Центр содействия научно-учебной деятельности<br/><br/></div>
                                <a href="#" class="g_t"><div class="to_section" >
                                        <img class="g_t_img" src="/resources/main-theme/img/to_sect.png" width="12" height="10"/>
                                        <span class="g_t">Перейти к разделу</span>
                                </div></a>
                        </div>
                </div>
        </div>
        <div class="tile">
                <div class="tile_wraper">
                        <a href="#" class="g_t_sen"><div class="tile_main">
                                <img src="/resources/main-theme/img/tiles/profkom_cat.jpg" width="180" height="146">
                                <div class="inf_for">Студпрофком</div>
                        </div></a>
                        <div class="tile_sub">
                                <div class="head_sub" >Студенческий профсоюзный комитет</div>
                                <div class="text_sub"><br/>Первичная профсоюзная организация студентов<br/><br/></div>
                                <a href="#" class="g_t"><div class="to_section" >
                                        <img class="g_t_img" src="/resources/main-theme/img/to_sect.png" width="12" height="10"/>
                                        <span class="g_t">Перейти к разделу</span>
                                </div></a>
                        </div>
                </div>
        </div>
        <div class="tile">
                <div class="tile_wraper">
                        <a href="#" class="g_t_sen"><div class="tile_main">
                                <img src="/resources/main-theme/img/tiles/partners_cat.jpg" width="180" height="146">
                                <div class="inf_for">Наши партнеры</div>
                        </div></a>
                        <div class="tile_sub">
                                <div class="head_sub" >Наши партнеры</div>
                                <div class="text_sub"><br/><br/> Вузы -партнеры ЗабГУ Сибирского федерального округа<br/><br/></div>
                                <a href="#" class="g_t"><div class="to_section" >
                                        <img class="g_t_img" src="/resources/main-theme/img/to_sect.png" width="12" height="10"/>
                                        <span class="g_t">Перейти к разделу</span>
                                </div></a>
                        </div>
                </div>
        </div>
    </div>
                       


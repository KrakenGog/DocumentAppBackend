[Use-Case Diagram Erida.drawio](https://github.com/user-attachments/files/22585181/Use-Case.Diagram.Erida.drawio)# Erida

## Описание проекта
Проект представляет собой корпоративную платформу для управления договорной деятельностью и внутренним документооборотом. Он предназначен для автоматизации процессов работы с договорами, централизованного хранения документов и снижения рисков.
Система предоставляет возможность регистрировать, хранить и отслеживать договоры, а также автоматически генерировать на их основе сопутствующие документы, такие как акты выполненных работ. Для администраторов система позволяет настраивать права доступа пользователей через создание и назначение именованных наборов прав.
Неавторизованные пользователи (Гости) могут авторизоваться в системе, чтобы получить доступ к ее функционалу.
Пользователи могут взаимодействовать друг с другом через встроенный мессенджер, обмениваться документами и выполнять действия в рамках выданных им прав. 
Администраторы занимаются добавлением новых шаблонов договоров, регистрацией договоров в базе данных (БД) и генерацией актов. Они могут читать и редактировать любые данные в БД для поддержания их актуальности, а также выдавать пользователям доступ к части или всем правам администратора и конфигурировать и сохранять наборы разрешений (роли) для стандартизации управления доступом.
Это помогает организации эффективно управлять документами, контролировать доступ к информации и автоматизировать рутинные операции.

## Стек используемых технологий

### Backend
- **Фреймворк**: Spring Boot
- **Язык**: Java

### Frontend
- **Фреймворк**: React
- **Язык**: JavaScript

Схема базы данных
![photo_5391297358488269894_y](https://github.com/user-attachments/assets/4c4f2f5c-9679-43aa-bdd2-b06e2cd71602)



## Примеры использования и роли

[Upload<mxfile host="app.diagrams.net" agent="Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:143.0) Gecko/20100101 Firefox/143.0" version="28.2.5">
  <diagram name="Страница — 1" id="viUAxTYOAMvD502hQ9XU">
    <mxGraphModel grid="1" page="1" gridSize="10" guides="1" tooltips="1" connect="1" arrows="1" fold="1" pageScale="1" pageWidth="827" pageHeight="1169" math="0" shadow="0">
      <root>
        <mxCell id="0" />
        <mxCell id="1" parent="0" />
        <mxCell id="b1jvxYijN9P2EA6jAzJ2-14" value="User" style="shape=umlActor;verticalLabelPosition=bottom;verticalAlign=top;html=1;" vertex="1" parent="1">
          <mxGeometry x="625" y="20" width="30" height="60" as="geometry" />
        </mxCell>
        <mxCell id="b1jvxYijN9P2EA6jAzJ2-16" value="&lt;div&gt;Guest&lt;/div&gt;&lt;div&gt;&lt;br&gt;&lt;/div&gt;" style="shape=umlActor;verticalLabelPosition=bottom;verticalAlign=top;html=1;" vertex="1" parent="1">
          <mxGeometry x="230" y="40" width="30" height="60" as="geometry" />
        </mxCell>
        <mxCell id="b1jvxYijN9P2EA6jAzJ2-17" value="&lt;div&gt;Войти в&amp;nbsp;&lt;/div&gt;&lt;div&gt;систему&lt;/div&gt;" style="ellipse;whiteSpace=wrap;html=1;verticalAlign=top;" vertex="1" parent="1">
          <mxGeometry x="290" y="150" width="92.5" height="40" as="geometry" />
        </mxCell>
        <mxCell id="b1jvxYijN9P2EA6jAzJ2-18" value="Зарегистрироваться" style="ellipse;whiteSpace=wrap;html=1;verticalAlign=top;" vertex="1" parent="1">
          <mxGeometry x="100" y="150" width="150" height="30" as="geometry" />
        </mxCell>
        <mxCell id="b1jvxYijN9P2EA6jAzJ2-20" value="&lt;div&gt;Общаться в&amp;nbsp;&lt;/div&gt;&lt;div&gt;мессенджере&lt;/div&gt;" style="ellipse;whiteSpace=wrap;html=1;verticalAlign=top;" vertex="1" parent="1">
          <mxGeometry x="695" y="100" width="105" height="40" as="geometry" />
        </mxCell>
        <mxCell id="b1jvxYijN9P2EA6jAzJ2-22" value="Выйти из аккаунта" style="ellipse;whiteSpace=wrap;html=1;verticalAlign=top;" vertex="1" parent="1">
          <mxGeometry x="490" y="90" width="80" height="50" as="geometry" />
        </mxCell>
        <mxCell id="b1jvxYijN9P2EA6jAzJ2-24" value="&lt;div&gt;Использовать&lt;/div&gt;&lt;div&gt;выданные&amp;nbsp;&lt;/div&gt;&lt;div&gt;права&lt;/div&gt;" style="ellipse;whiteSpace=wrap;html=1;verticalAlign=top;" vertex="1" parent="1">
          <mxGeometry x="605" y="170" width="125" height="60" as="geometry" />
        </mxCell>
        <mxCell id="b1jvxYijN9P2EA6jAzJ2-26" value="Admin" style="shape=umlActor;verticalLabelPosition=bottom;verticalAlign=top;html=1;" vertex="1" parent="1">
          <mxGeometry x="250" y="330" width="30" height="60" as="geometry" />
        </mxCell>
        <mxCell id="b1jvxYijN9P2EA6jAzJ2-28" value="&lt;div&gt;Управлять&amp;nbsp;&lt;/div&gt;&lt;div&gt;шаблонами&amp;nbsp;&lt;/div&gt;&lt;div&gt;договоров&lt;/div&gt;" style="ellipse;whiteSpace=wrap;html=1;verticalAlign=top;" vertex="1" parent="1">
          <mxGeometry x="335" y="330" width="125" height="60" as="geometry" />
        </mxCell>
        <mxCell id="b1jvxYijN9P2EA6jAzJ2-30" value="&lt;div&gt;Управлять&amp;nbsp;&lt;/div&gt;&lt;div&gt;правами доступа&amp;nbsp;&lt;/div&gt;&lt;div&gt;пользователей&lt;/div&gt;" style="ellipse;whiteSpace=wrap;html=1;verticalAlign=top;" vertex="1" parent="1">
          <mxGeometry x="60" y="320" width="135" height="70" as="geometry" />
        </mxCell>
        <mxCell id="b1jvxYijN9P2EA6jAzJ2-32" value="&lt;div&gt;Управлять&lt;/div&gt;&lt;div&gt;договорами&lt;/div&gt;" style="ellipse;whiteSpace=wrap;html=1;verticalAlign=top;" vertex="1" parent="1">
          <mxGeometry x="335" y="250" width="110" height="40" as="geometry" />
        </mxCell>
        <mxCell id="b1jvxYijN9P2EA6jAzJ2-35" value="&lt;div&gt;Просматривать и редактировать&lt;/div&gt;&lt;div&gt;данные в бд&lt;/div&gt;" style="ellipse;whiteSpace=wrap;html=1;verticalAlign=top;" vertex="1" parent="1">
          <mxGeometry x="160" y="230" width="140" height="60" as="geometry" />
        </mxCell>
        <mxCell id="b1jvxYijN9P2EA6jAzJ2-38" value="&lt;div&gt;Генерировать&lt;/div&gt;&lt;div&gt;акты на основе&amp;nbsp;&lt;/div&gt;&lt;div&gt;договоров&lt;/div&gt;" style="ellipse;whiteSpace=wrap;html=1;verticalAlign=top;" vertex="1" parent="1">
          <mxGeometry x="220" y="440" width="130" height="60" as="geometry" />
        </mxCell>
        <mxCell id="b1jvxYijN9P2EA6jAzJ2-42" value="" style="endArrow=none;html=1;rounded=0;exitX=1;exitY=0.3333333333333333;exitDx=0;exitDy=0;exitPerimeter=0;entryX=0;entryY=0.5;entryDx=0;entryDy=0;" edge="1" parent="1" source="b1jvxYijN9P2EA6jAzJ2-26" target="b1jvxYijN9P2EA6jAzJ2-28">
          <mxGeometry width="50" height="50" relative="1" as="geometry">
            <mxPoint x="350" y="410" as="sourcePoint" />
            <mxPoint x="400" y="360" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="b1jvxYijN9P2EA6jAzJ2-43" value="" style="endArrow=none;html=1;rounded=0;entryX=0.295;entryY=0.975;entryDx=0;entryDy=0;entryPerimeter=0;" edge="1" parent="1" target="b1jvxYijN9P2EA6jAzJ2-32">
          <mxGeometry width="50" height="50" relative="1" as="geometry">
            <mxPoint x="280" y="350" as="sourcePoint" />
            <mxPoint x="400" y="360" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="b1jvxYijN9P2EA6jAzJ2-44" value="" style="endArrow=none;html=1;rounded=0;entryX=0;entryY=0.3333333333333333;entryDx=0;entryDy=0;entryPerimeter=0;" edge="1" parent="1" target="b1jvxYijN9P2EA6jAzJ2-26">
          <mxGeometry width="50" height="50" relative="1" as="geometry">
            <mxPoint x="195" y="360" as="sourcePoint" />
            <mxPoint x="245" y="310" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="b1jvxYijN9P2EA6jAzJ2-45" value="" style="endArrow=none;html=1;rounded=0;exitX=0;exitY=0.3333333333333333;exitDx=0;exitDy=0;exitPerimeter=0;entryX=0.678;entryY=0.922;entryDx=0;entryDy=0;entryPerimeter=0;" edge="1" parent="1" source="b1jvxYijN9P2EA6jAzJ2-26" target="b1jvxYijN9P2EA6jAzJ2-35">
          <mxGeometry width="50" height="50" relative="1" as="geometry">
            <mxPoint x="350" y="370" as="sourcePoint" />
            <mxPoint x="400" y="320" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="b1jvxYijN9P2EA6jAzJ2-47" value="" style="endArrow=none;html=1;rounded=0;entryX=1;entryY=0;entryDx=0;entryDy=0;exitX=0.888;exitY=0.362;exitDx=0;exitDy=0;exitPerimeter=0;" edge="1" parent="1" source="b1jvxYijN9P2EA6jAzJ2-26" target="b1jvxYijN9P2EA6jAzJ2-38">
          <mxGeometry width="50" height="50" relative="1" as="geometry">
            <mxPoint x="290" y="350" as="sourcePoint" />
            <mxPoint x="400" y="350" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="b1jvxYijN9P2EA6jAzJ2-48" value="" style="endArrow=none;html=1;rounded=0;exitX=0.5;exitY=0;exitDx=0;exitDy=0;" edge="1" parent="1" source="b1jvxYijN9P2EA6jAzJ2-22" target="b1jvxYijN9P2EA6jAzJ2-14">
          <mxGeometry width="50" height="50" relative="1" as="geometry">
            <mxPoint x="600" y="170" as="sourcePoint" />
            <mxPoint x="650" y="120" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="b1jvxYijN9P2EA6jAzJ2-49" value="" style="endArrow=none;html=1;rounded=0;exitX=0.36;exitY=0;exitDx=0;exitDy=0;exitPerimeter=0;" edge="1" parent="1" source="b1jvxYijN9P2EA6jAzJ2-24" target="b1jvxYijN9P2EA6jAzJ2-14">
          <mxGeometry width="50" height="50" relative="1" as="geometry">
            <mxPoint x="600" y="170" as="sourcePoint" />
            <mxPoint x="650" y="120" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="b1jvxYijN9P2EA6jAzJ2-50" value="" style="endArrow=none;html=1;rounded=0;entryX=0.424;entryY=0.01;entryDx=0;entryDy=0;entryPerimeter=0;" edge="1" parent="1" source="b1jvxYijN9P2EA6jAzJ2-14" target="b1jvxYijN9P2EA6jAzJ2-20">
          <mxGeometry width="50" height="50" relative="1" as="geometry">
            <mxPoint x="600" y="170" as="sourcePoint" />
            <mxPoint x="732" y="99" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="b1jvxYijN9P2EA6jAzJ2-51" value="" style="endArrow=none;html=1;rounded=0;exitX=0.419;exitY=0.014;exitDx=0;exitDy=0;exitPerimeter=0;" edge="1" parent="1" source="b1jvxYijN9P2EA6jAzJ2-18" target="b1jvxYijN9P2EA6jAzJ2-16">
          <mxGeometry width="50" height="50" relative="1" as="geometry">
            <mxPoint x="240" y="170" as="sourcePoint" />
            <mxPoint x="290" y="120" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="b1jvxYijN9P2EA6jAzJ2-52" value="" style="endArrow=none;html=1;rounded=0;" edge="1" parent="1" source="b1jvxYijN9P2EA6jAzJ2-16">
          <mxGeometry width="50" height="50" relative="1" as="geometry">
            <mxPoint x="240" y="170" as="sourcePoint" />
            <mxPoint x="336" y="150" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="b1jvxYijN9P2EA6jAzJ2-53" value="&lt;div&gt;Общаться в&amp;nbsp;&lt;/div&gt;&lt;div&gt;мессенджере&lt;/div&gt;" style="ellipse;whiteSpace=wrap;html=1;" vertex="1" parent="1">
          <mxGeometry x="590" y="330" width="115" height="50" as="geometry" />
        </mxCell>
        <mxCell id="b1jvxYijN9P2EA6jAzJ2-54" value="" style="endArrow=block;dashed=1;endFill=0;endSize=12;html=1;rounded=0;exitX=0.428;exitY=0.989;exitDx=0;exitDy=0;exitPerimeter=0;" edge="1" parent="1" source="b1jvxYijN9P2EA6jAzJ2-53">
          <mxGeometry width="160" relative="1" as="geometry">
            <mxPoint x="510" y="390" as="sourcePoint" />
            <mxPoint x="580" y="410" as="targetPoint" />
            <Array as="points">
              <mxPoint x="580" y="410" />
            </Array>
          </mxGeometry>
        </mxCell>
        <mxCell id="b1jvxYijN9P2EA6jAzJ2-55" value="Отправлять сообщения" style="ellipse;whiteSpace=wrap;html=1;" vertex="1" parent="1">
          <mxGeometry x="500" y="410" width="125" height="50" as="geometry" />
        </mxCell>
        <mxCell id="b1jvxYijN9P2EA6jAzJ2-59" value="" style="endArrow=block;dashed=1;endFill=0;endSize=12;html=1;rounded=0;exitX=0.619;exitY=1.05;exitDx=0;exitDy=0;exitPerimeter=0;" edge="1" parent="1" source="b1jvxYijN9P2EA6jAzJ2-53" target="b1jvxYijN9P2EA6jAzJ2-60">
          <mxGeometry width="160" relative="1" as="geometry">
            <mxPoint x="510" y="360" as="sourcePoint" />
            <mxPoint x="670" y="360" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="b1jvxYijN9P2EA6jAzJ2-60" value="Отправлять договоры и акты" style="ellipse;whiteSpace=wrap;html=1;" vertex="1" parent="1">
          <mxGeometry x="695" y="400" width="115" height="60" as="geometry" />
        </mxCell>
        <mxCell id="b1jvxYijN9P2EA6jAzJ2-61" value="Использовать выданные права" style="ellipse;whiteSpace=wrap;html=1;" vertex="1" parent="1">
          <mxGeometry x="330" y="750" width="120" height="60" as="geometry" />
        </mxCell>
        <mxCell id="b1jvxYijN9P2EA6jAzJ2-62" value="&lt;div&gt;Управлять&lt;/div&gt;&lt;div&gt;договорами&lt;/div&gt;" style="ellipse;whiteSpace=wrap;html=1;verticalAlign=top;" vertex="1" parent="1">
          <mxGeometry x="530" y="760" width="110" height="40" as="geometry" />
        </mxCell>
        <mxCell id="b1jvxYijN9P2EA6jAzJ2-63" value="&lt;div&gt;Управлять&amp;nbsp;&lt;/div&gt;&lt;div&gt;шаблонами&amp;nbsp;&lt;/div&gt;&lt;div&gt;договоров&lt;/div&gt;" style="ellipse;whiteSpace=wrap;html=1;verticalAlign=top;" vertex="1" parent="1">
          <mxGeometry x="325" y="650" width="125" height="60" as="geometry" />
        </mxCell>
        <mxCell id="b1jvxYijN9P2EA6jAzJ2-64" value="&lt;div&gt;Генерировать&lt;/div&gt;&lt;div&gt;акты на основе&amp;nbsp;&lt;/div&gt;&lt;div&gt;договоров&lt;/div&gt;" style="ellipse;whiteSpace=wrap;html=1;verticalAlign=top;" vertex="1" parent="1">
          <mxGeometry x="132.5" y="710" width="127.5" height="60" as="geometry" />
        </mxCell>
        <mxCell id="b1jvxYijN9P2EA6jAzJ2-66" value="" style="endArrow=block;dashed=1;endFill=0;endSize=12;html=1;rounded=0;exitX=0.5;exitY=0;exitDx=0;exitDy=0;entryX=0.5;entryY=1;entryDx=0;entryDy=0;" edge="1" parent="1" source="b1jvxYijN9P2EA6jAzJ2-61" target="b1jvxYijN9P2EA6jAzJ2-63">
          <mxGeometry width="160" relative="1" as="geometry">
            <mxPoint x="350" y="650" as="sourcePoint" />
            <mxPoint x="510" y="650" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="b1jvxYijN9P2EA6jAzJ2-67" value="" style="endArrow=block;dashed=1;endFill=0;endSize=12;html=1;rounded=0;exitX=0;exitY=0.333;exitDx=0;exitDy=0;exitPerimeter=0;entryX=1;entryY=0.5;entryDx=0;entryDy=0;" edge="1" parent="1" source="b1jvxYijN9P2EA6jAzJ2-61" target="b1jvxYijN9P2EA6jAzJ2-64">
          <mxGeometry width="160" relative="1" as="geometry">
            <mxPoint x="350" y="650" as="sourcePoint" />
            <mxPoint x="510" y="650" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="b1jvxYijN9P2EA6jAzJ2-68" value="" style="endArrow=block;dashed=1;endFill=0;endSize=12;html=1;rounded=0;exitX=1;exitY=0.5;exitDx=0;exitDy=0;entryX=0;entryY=0.5;entryDx=0;entryDy=0;" edge="1" parent="1" source="b1jvxYijN9P2EA6jAzJ2-61" target="b1jvxYijN9P2EA6jAzJ2-62">
          <mxGeometry width="160" relative="1" as="geometry">
            <mxPoint x="350" y="650" as="sourcePoint" />
            <mxPoint x="510" y="650" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="b1jvxYijN9P2EA6jAzJ2-69" value="&lt;div&gt;Просматривать и редактировать&lt;/div&gt;&lt;div&gt;данные в бд&lt;/div&gt;" style="ellipse;whiteSpace=wrap;html=1;verticalAlign=top;" vertex="1" parent="1">
          <mxGeometry x="170" y="810" width="140" height="60" as="geometry" />
        </mxCell>
        <mxCell id="b1jvxYijN9P2EA6jAzJ2-70" value="" style="endArrow=block;dashed=1;endFill=0;endSize=12;html=1;rounded=0;exitX=0;exitY=0.5;exitDx=0;exitDy=0;entryX=0.772;entryY=0.102;entryDx=0;entryDy=0;entryPerimeter=0;" edge="1" parent="1" source="b1jvxYijN9P2EA6jAzJ2-61" target="b1jvxYijN9P2EA6jAzJ2-69">
          <mxGeometry width="160" relative="1" as="geometry">
            <mxPoint x="350" y="840" as="sourcePoint" />
            <mxPoint x="510" y="840" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="b1jvxYijN9P2EA6jAzJ2-71" value="&lt;div&gt;Управлять&amp;nbsp;&lt;/div&gt;&lt;div&gt;правами доступа&amp;nbsp;&lt;/div&gt;&lt;div&gt;пользователей&lt;/div&gt;" style="ellipse;whiteSpace=wrap;html=1;verticalAlign=top;" vertex="1" parent="1">
          <mxGeometry x="470" y="980" width="145" height="60" as="geometry" />
        </mxCell>
        <mxCell id="b1jvxYijN9P2EA6jAzJ2-72" value="Добавить шаблон договора" style="ellipse;whiteSpace=wrap;html=1;" vertex="1" parent="1">
          <mxGeometry x="215" y="594.5" width="115" height="55.5" as="geometry" />
        </mxCell>
        <mxCell id="b1jvxYijN9P2EA6jAzJ2-73" value="Редактировать шаблон договора" style="ellipse;whiteSpace=wrap;html=1;" vertex="1" parent="1">
          <mxGeometry x="450" y="589.25" width="140" height="66" as="geometry" />
        </mxCell>
        <mxCell id="b1jvxYijN9P2EA6jAzJ2-74" value="" style="endArrow=block;dashed=1;endFill=0;endSize=12;html=1;rounded=0;exitX=0.289;exitY=0.027;exitDx=0;exitDy=0;exitPerimeter=0;entryX=1;entryY=0.5;entryDx=0;entryDy=0;" edge="1" parent="1" source="b1jvxYijN9P2EA6jAzJ2-63" target="b1jvxYijN9P2EA6jAzJ2-72">
          <mxGeometry width="160" relative="1" as="geometry">
            <mxPoint x="350" y="680" as="sourcePoint" />
            <mxPoint x="510" y="680" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="b1jvxYijN9P2EA6jAzJ2-75" value="" style="endArrow=block;dashed=1;endFill=0;endSize=12;html=1;rounded=0;entryX=0;entryY=0.5;entryDx=0;entryDy=0;exitX=0.684;exitY=-0.021;exitDx=0;exitDy=0;exitPerimeter=0;" edge="1" parent="1" source="b1jvxYijN9P2EA6jAzJ2-63" target="b1jvxYijN9P2EA6jAzJ2-73">
          <mxGeometry width="160" relative="1" as="geometry">
            <mxPoint x="350" y="680" as="sourcePoint" />
            <mxPoint x="510" y="680" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="b1jvxYijN9P2EA6jAzJ2-76" value="Создавать и регистрировать договоры" style="ellipse;whiteSpace=wrap;html=1;" vertex="1" parent="1">
          <mxGeometry x="665" y="680" width="135" height="60" as="geometry" />
        </mxCell>
        <mxCell id="b1jvxYijN9P2EA6jAzJ2-77" value="Просматривать существующие договоры&amp;nbsp;" style="ellipse;whiteSpace=wrap;html=1;" vertex="1" parent="1">
          <mxGeometry x="680" y="790" width="130" height="60" as="geometry" />
        </mxCell>
        <mxCell id="b1jvxYijN9P2EA6jAzJ2-78" value="" style="endArrow=block;dashed=1;endFill=0;endSize=12;html=1;rounded=0;entryX=0.043;entryY=0.599;entryDx=0;entryDy=0;entryPerimeter=0;" edge="1" parent="1" source="b1jvxYijN9P2EA6jAzJ2-62" target="b1jvxYijN9P2EA6jAzJ2-76">
          <mxGeometry width="160" relative="1" as="geometry">
            <mxPoint x="350" y="750" as="sourcePoint" />
            <mxPoint x="510" y="750" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="b1jvxYijN9P2EA6jAzJ2-80" value="" style="endArrow=block;dashed=1;endFill=0;endSize=12;html=1;rounded=0;exitX=0.911;exitY=0.797;exitDx=0;exitDy=0;exitPerimeter=0;" edge="1" parent="1" source="b1jvxYijN9P2EA6jAzJ2-62" target="b1jvxYijN9P2EA6jAzJ2-77">
          <mxGeometry width="160" relative="1" as="geometry">
            <mxPoint x="350" y="750" as="sourcePoint" />
            <mxPoint x="510" y="750" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="b1jvxYijN9P2EA6jAzJ2-81" value="Выдавать права пользователям" style="ellipse;whiteSpace=wrap;html=1;" vertex="1" parent="1">
          <mxGeometry x="370" y="900" width="130" height="60" as="geometry" />
        </mxCell>
        <mxCell id="b1jvxYijN9P2EA6jAzJ2-82" value="Лишать пользователей прав" style="ellipse;whiteSpace=wrap;html=1;" vertex="1" parent="1">
          <mxGeometry x="582.5" y="900" width="130" height="60" as="geometry" />
        </mxCell>
        <mxCell id="b1jvxYijN9P2EA6jAzJ2-83" value="Создавать наборы прав (роли)" style="ellipse;whiteSpace=wrap;html=1;" vertex="1" parent="1">
          <mxGeometry x="482.5" y="1090" width="120" height="60" as="geometry" />
        </mxCell>
        <mxCell id="b1jvxYijN9P2EA6jAzJ2-84" value="Назначать пользователям роли" style="ellipse;whiteSpace=wrap;html=1;" vertex="1" parent="1">
          <mxGeometry x="350" y="1040" width="110" height="70" as="geometry" />
        </mxCell>
        <mxCell id="b1jvxYijN9P2EA6jAzJ2-85" value="Лишать пользователей ролей" style="ellipse;whiteSpace=wrap;html=1;" vertex="1" parent="1">
          <mxGeometry x="625" y="1040" width="115" height="70" as="geometry" />
        </mxCell>
        <mxCell id="b1jvxYijN9P2EA6jAzJ2-86" value="" style="endArrow=block;dashed=1;endFill=0;endSize=12;html=1;rounded=0;exitX=0.345;exitY=0;exitDx=0;exitDy=0;exitPerimeter=0;entryX=0.806;entryY=0.923;entryDx=0;entryDy=0;entryPerimeter=0;" edge="1" parent="1" source="b1jvxYijN9P2EA6jAzJ2-71" target="b1jvxYijN9P2EA6jAzJ2-81">
          <mxGeometry width="160" relative="1" as="geometry">
            <mxPoint x="350" y="1010" as="sourcePoint" />
            <mxPoint x="510" y="1010" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="b1jvxYijN9P2EA6jAzJ2-87" value="" style="endArrow=block;dashed=1;endFill=0;endSize=12;html=1;rounded=0;" edge="1" parent="1" target="b1jvxYijN9P2EA6jAzJ2-82">
          <mxGeometry width="160" relative="1" as="geometry">
            <mxPoint x="570" y="980" as="sourcePoint" />
            <mxPoint x="510" y="1010" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="b1jvxYijN9P2EA6jAzJ2-88" value="" style="endArrow=block;dashed=1;endFill=0;endSize=12;html=1;rounded=0;entryX=0.909;entryY=0.143;entryDx=0;entryDy=0;entryPerimeter=0;exitX=0.057;exitY=0.71;exitDx=0;exitDy=0;exitPerimeter=0;" edge="1" parent="1" source="b1jvxYijN9P2EA6jAzJ2-71" target="b1jvxYijN9P2EA6jAzJ2-84">
          <mxGeometry width="160" relative="1" as="geometry">
            <mxPoint x="350" y="1010" as="sourcePoint" />
            <mxPoint x="510" y="1010" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="b1jvxYijN9P2EA6jAzJ2-89" value="" style="endArrow=block;dashed=1;endFill=0;endSize=12;html=1;rounded=0;entryX=0.5;entryY=0;entryDx=0;entryDy=0;exitX=0.5;exitY=1;exitDx=0;exitDy=0;" edge="1" parent="1" source="b1jvxYijN9P2EA6jAzJ2-71" target="b1jvxYijN9P2EA6jAzJ2-83">
          <mxGeometry width="160" relative="1" as="geometry">
            <mxPoint x="350" y="1010" as="sourcePoint" />
            <mxPoint x="510" y="1010" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="b1jvxYijN9P2EA6jAzJ2-90" value="" style="endArrow=block;dashed=1;endFill=0;endSize=12;html=1;rounded=0;exitX=0.773;exitY=0.923;exitDx=0;exitDy=0;exitPerimeter=0;" edge="1" parent="1" source="b1jvxYijN9P2EA6jAzJ2-71" target="b1jvxYijN9P2EA6jAzJ2-85">
          <mxGeometry width="160" relative="1" as="geometry">
            <mxPoint x="350" y="1010" as="sourcePoint" />
            <mxPoint x="510" y="1010" as="targetPoint" />
          </mxGeometry>
        </mxCell>
      </root>
    </mxGraphModel>
  </diagram>
</mxfile>
ing Use-Case Diagram Erida.drawio…]()


## API Documentation

### Базовый URL

## API сервера

### Основные endpoints:
- `POST /api/auth/login` - Авторизация
- `POST /api/auth/register` - Регистрация
- `GET /api/organizations` - Справочник организаций
- `GET /api/banks` - Справочник банков
- `GET /api/bank-accounts` - Справочник счетов
- `GET /api/contracts` - Справочник договоров
- `GET /api/contracts/filter` - Фильтрация договоров

# API Documentation

## Аутентификация

### Регистрация
```http
POST /api/auth/register
Content-Type: application/json

{
  "username": "string",
  "password": "string",
  "email": "string"
}
```

### Авторизация
```http
POST /api/auth/login
Content-Type: application/json

{
  "username": "string",
  "password": "string"
}
```

Response:
```json
{
  "token": "string",
  "expires_in": 3600
}
```

## НСИ - Нормативно-справочная информация

### Справочник организаций
```http
GET /api/organizations
Authorization: Bearer {token}
```

Response:
```json
[
  {
    "unp": "123456789",
    "full_name": "Полное наименование организации",
    "short_name": "Сокращенное наименование",
    "phone": "+375 29 123-45-67",
    "address": "Адрес организации",
    "legal_address": "Юридический адрес",
    "postal_address": "Почтовый адрес"
  }
]
```

### Справочник банков
```http
GET /api/banks
Authorization: Bearer {token}
```

Response:
```json
[
  {
    "id": 1,
    "bic": "BELBBY2X",
    "bank_name": "Наименование банка"
  }
]
```

### Справочник счетов
```http
GET /api/bank-accounts
Authorization: Bearer {token}
```

Response:
```json
[
  {
    "id": 1,
    "unp": "123456789",
    "account_number": "BY12ALFA20345678901234567890",
    "bic": "BELBBY2X",
    "bank_name": "Наименование банка",
    "is_budget": true
  }
]
```

### Справочник видов услуг
```http
GET /api/services
Authorization: Bearer {token}
```

Response:
```json
[
  {
    "id": 1,
    "code": "SRV001",
    "name": "Наименование услуги",
    "unit": "шт.",
    "cost_without_vat": 100.00,
    "vat": 20.00,
    "cost_with_vat": 120.00
  }
]
```

### Справочник договоров
```http
GET /api/contracts
Authorization: Bearer {token}
```

Response:
```json
[
  {
    "contract_number": "ДГ-2024-001",
    "contract_date": "2024-01-15",
    "valid_from": "2024-02-01",
    "valid_to": "2024-12-31",
    "unp": "123456789",
    "organization_full_name": "Полное наименование организации",
    "service_type": "Вид услуг",
    "workplaces_count": 10,
    "funding_source": "бюджет",
    "electronic_acts": true,
    "act_prefix": "АКТ"
  }
]
```

## Фильтрация договоров

### Фильтрация по дате

#### Действующие договоры
```http
GET /api/contracts/active
Authorization: Bearer {token}
```

#### Договоры за период
```http
GET /api/contracts/filter-by-date
Authorization: Bearer {token}
Content-Type: application/json

{
  "start_date": "2024-01-01",
  "end_date": "2024-12-31"
}
```

### Фильтрация по типу договора
```http
GET /api/contracts/filter-by-type
Authorization: Bearer {token}
Content-Type: application/json

{
  "contract_type": "тип_договора"
}
```

### Фильтрация по типу расторжения
```http
GET /api/contracts/filter-by-termination
Authorization: Bearer {token}
Content-Type: application/json

{
  "termination_type": "тип_расторжения"
}
```

### Комплексная фильтрация
```http
GET /api/contracts/filter
Authorization: Bearer {token}
Content-Type: application/json

{
  "start_date": "2024-01-01",
  "end_date": "2024-12-31",
  "contract_type": "тип_договора",
  "termination_type": "тип_расторжения",
  "funding_source": "бюджет",
  "electronic_acts": true
}
```

## Модели данных

### Организация
- `unp` (string, 9 цифр) - УНП, уникальный
- `full_name` (string, 255) - Полное наименование, обязательно
- `short_name` (string, 255) - Сокращенное наименование, обязательно
- `address` (string, 255, optional) - Адрес
- `legal_address` (string, 255, optional) - Юридический адрес
- `postal_address` (string, 255, optional) - Почтовый адрес
- `phone` (string, 255, optional) - Телефон

### Банк
- `id` (integer) - Уникальный порядковый номер
- `bic` (string, ≤8) - БИК, латинские заглавные буквы и цифры
- `bank_name` (string, 255) - Наименование банка

### Счет
- `id` (integer) - Уникальный порядковый номер
- `unp` (string, 9 цифр) - УНП организации
- `account_number` (string, 28) - Номер счета (2 буквы + 2 цифры + 4 буквы + 20 цифр)
- `bank_id` (integer) - Ссылка на банк
- `is_budget` (boolean) - Признак "Бюджет/внебюджет" (1/0)

### Договор
- `id` (integer) - Уникальный номер
- `contract_number` (string, 20) - Номер договора
- `contract_type` (string) - Тип договора
- `contract_date` (date) - Дата договора
- `valid_from` (date) - Действует с
- `valid_to` (date) - Действует по
- `bank_account_id` (integer) - Ссылка на счет
- `service_id` (integer) - Ссылка на услугу
- `workplaces_count` (integer) - Количество рабочих мест
- `electronic_acts` (boolean) - Признак "ЭлектроАкты"
- `termination_type` (string) - Тип окончания договора

### Услуга
- `id` (integer) - Уникальный порядковый номер
- `name` (string) - Название
- `cost_without_vat` (decimal) - Стоимость без НДС
- `unit` (string) - Единица измерения

## Коды ответов

- `200` - Успешный запрос
- `201` - Успешное создание
- `400` - Неверный запрос
- `401` - Не авторизован
- `404` - Ресурс не найден
- `500` - Внутренняя ошибка сервера

## Заголовки

Все запросы (кроме аутентификации) требуют заголовок авторизации:
```
Authorization: Bearer {token}
```

Для запросов с телом требуется указать:
```
Content-Type: application/json
```

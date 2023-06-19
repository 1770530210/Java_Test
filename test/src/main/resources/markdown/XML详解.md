# XML详解

xml（可拓展标记性语言）

XML 被设计用来传输和存储数据。

HTML 被设计用来显示数据。

XML 文档形成一种树结构

## 特点

1.xml具有平台无关性（windows，linux），是一门独立的语言

2.xml面世后的所有语言都支持xml

3.xml具有自我描述性（内容自定义）

（1）html文件中，所有元素标签都是官方定义好的，直接引用

（2）xml文件中，所有元素自定义

## 语法规则

1.xml文件必须有且仅有一个根元素

```
正确：
<?xml version="1.0" encoding="UTF-8"?>
<root>
	<child></child>
	...
</root>

错误：
<?xml version="1.0" encoding="UTF-8"?>
<root>
	...
</root>
<root>
	...
</root>
```

2.xml元素（标签）有开必然有合（结束标签）

```
正确：<label></label>
错误：<label/>
```

3.xml元素大小写敏感

```
a元素：<a></a>
A元素：<A></A>
```

4.xml元素必须正确嵌套

```
正确：
<a>
	<b>
	</b>
</a>
错误：
<a>
	<b>
</a>
	</b>
```

5.xml元素的属性必须加引号

```
正确：<a type="a" id="1">
错误：<a type=a id=1>
```



## 标准的xml文件格式

XML声明：

定义了 XML 的版本（1.0）和所使用的编码（UTF-8 : 万国码, 可显示各种语言）。

```
<?xml version="1.0" encoding="UTF-8"?>
```

自定义的根元素:

```
<root>
</root>
```

注释：

```
<!-- TODO -->
```

## 字符引用

十进制	&#

十六进制	&#x

## 实体引用

在 XML 中，一些字符拥有特殊的意义。

如果您把字符 "<" 放在 XML 元素中，会发生错误，这是因为解析器会把它当作新元素的开始。

这样会产生 XML 错误：

为了避免这个错误，请用实体引用来代替 "<" 字符：

在 XML 中，有 5 个预定义的实体引用：

| &lt;   | <    | less than      |
| ------ | ---- | -------------- |
| &gt;   | >    | greater than   |
| &amp;  | &    | ampersand      |
| &apos; | '    | apostrophe     |
| &quot; | "    | quotation mark |

**注释：**在 XML 中，只有字符 "<" 和 "&" 确实是非法的。大于号是合法的，但是用实体引用来代替它是一个好习惯

## PCDATA - 被解析的字符数据

## CDATA - （未解析）字符数据

XML 文档中的所有文本均会被解析器解析。

只有 CDATA 区段中的文本会被解析器忽略。

使用<![CDATA[ 和 ]]>来限定其界限

可以用其来囊括含有<、>、&之类字符的字符串，而不需要将其解释为标记

```
<![CdATA[	<&>	]]>
```

## 处理指令

专门在处理XML文当的应用程序中使用的之类通过<?和?>限定其界限

例如：

```
<?xml version="1.0" encoding="UTF-8"?>
<?xml-stylesheet href="mystyle.css" type="text/css"?>
```

**在 XML 中，空格会被保留**

## **XML 以 LF 存储换行**

在 Windows 应用程序中，换行通常以一对字符来存储：回车符（CR）和换行符（LF）。

在 Unix 和 Mac OSX 中，使用 LF 来存储新行。

在旧的 Mac 系统中，使用 CR 来存储新行。

XML 以 LF 存储换行。

##  XML 元素

XML 元素指的是从（且包括）开始标签直到（且包括）结束标签的部分。

一个元素可以包含：

- 其他元素
- 文本
- 属性
- 或混合以上所有...

## XML 命名规则

XML 元素必须遵循以下命名规则：

- 名称可以包含字母、数字以及其他的字符
- 名称不能以数字或者标点符号开始
- 名称不能以字母 xml（或者 XML、Xml 等等）开始
- 名称不能包含空格

可使用任何名称，没有保留的字词。

## 最佳命名习惯

使名称具有描述性。使用下划线的名称也很不错：<first_name>、<last_name>。

名称应简短和简单，比如：<book_title>，而不是：<the_title_of_the_book>。

**避免 "-" 字符**。如果您按照这样的方式进行命名："first-name"，一些软件会认为您想要从 first 里边减去 name。

**避免 "." 字符**。如果您按照这样的方式进行命名："first.name"，一些软件会认为 "name" 是对象 "first" 的属性。

**避免 ":" 字符**。冒号会被转换为命名空间来使用（稍后介绍）。

XML 文档经常有一个对应的数据库，其中的字段会对应 XML 文档中的元素。有一个实用的经验，即使用数据库的命名规则来命名 XML 文档中的元素。

在 XML 中，éòá 等非英语字母是完全合法的，不过需要留意，您的软件供应商不支持这些字符时可能出现的问题。

## XML 元素是可扩展的

XML 的优势之一，就是可以在不中断应用程序的情况下进行扩展。

## XML 属性

XML元素具有属性，类似 HTML。

属性（Attribute）提供有关元素的额外信息。

## XML 属性必须加引号

属性值必须被引号包围，不过单引号和双引号均可使用。比如一个人的性别，person 元素可以这样写：

```
<person sex="female">
```

或者这样也可以：

```
<person sex='female'>
```

如果属性值本身包含双引号，您可以使用单引号，就像这个实例：

```
<gangster name='George "Shotgun" Ziegler'>
```

或者您可以使用字符实体：

```
<gangster name="George &quot;Shotgun&quot; Ziegler">
```

在 XML 中，您应该尽量避免使用属性。如果信息感觉起来很像数据，那么请使用元素吧。

因使用属性而引起的一些问题：

- 属性不能包含多个值（元素可以）
- 属性不能包含树结构（元素可以）
- 属性不容易扩展（为未来的变化）

## XML 验证

拥有正确语法的 XML 被称为"形式良好"的 XML。

通过 DTD 验证的XML是"合法"的 XML。

## 验证 XML 文档

合法的 XML 文档是"形式良好"的 XML 文档，这也符合文档类型定义（DTD）的规则：

```
<?xml version="1.0" encoding="ISO-8859-1"?>
<!DOCTYPE note SYSTEM "Note.dtd">
 <note>
 <to>Tove</to>
 <from>Jani</from>
 <heading>Reminder</heading>
 <body>Don't forget me this weekend!</body>
 </note>
```

在上面的实例中，DOCTYPE 声明是对外部 DTD 文件的引用。下面的段落展示了这个文件的内容。

------

## XML DTD

DTD 的目的是定义 XML 文档的结构。它使用一系列合法的元素来定义文档结构：

```
<!DOCTYPE note
 [
  <!ELEMENT note (to,from,heading,body)>
  <!ELEMENT to      (#PCDATA)>
  <!ELEMENT from    (#PCDATA)>
  <!ELEMENT heading (#PCDATA)>
  <!ELEMENT body    (#PCDATA)>
 ]>
```

如果您想要学习 DTD，请在我们的[首页](https://www.runoob.com/)查找 DTD 教程。

------

## XML Schema

W3C 支持一种基于 XML 的 DTD 代替者，它名为 XML Schema：**.xsd

```
<xs:element name="note">

 <xs:complexType>
  <xs:sequence>
    <xs:element name="to" type="xs:string"/>
    <xs:element name="from" type="xs:string"/>
    <xs:element name="heading" type="xs:string"/>
    <xs:element name="body" type="xs:string"/>
  </xs:sequence>
 </xs:complexType>

 </xs:element>
```

## 根据 DTD 来验证 XML

```
<?xml version="1.0" ?> 
<!DOCTYPE note [
  <!ELEMENT note (to,from,heading,body)>
  <!ELEMENT to      (#PCDATA)>
  <!ELEMENT from    (#PCDATA)>
  <!ELEMENT heading (#PCDATA)>
  <!ELEMENT body    (#PCDATA)>
]>
<note>
<to>Tove</to> 
<from>Jani</from> 
<heading>Reminder</heading> 
<message>Don't forget me this weekend!</message> 
</note>

```

## 使用 CSS 显示您的 XML

使用 CSS 来格式化 XML 文档是有可能的。

下面的实例就是关于如何使用 CSS 样式表来格式化 XML 文档：

请看这个 XML 文件：[CD 目录](https://www.runoob.com/try/xml/cd_catalog.xml)

然后看这个样式表：[CSS 文件](https://www.runoob.com/try/xml/cd_catalog.css)

最后，请查看：[使用 CSS 文件格式化的 CD 目录](https://www.runoob.com/try/xml/cd_catalog_with_css.xml)

下面是 XML 文件的一小部分。第二行把 XML 文件链接到 CSS 文件：

```
<?xml version="1.0" encoding="ISO-8859-1"?>
<?xml-stylesheet type="text/css" href="cd_catalog.css"?>
<CATALOG>
<CD>
<TITLE>Empire Burlesque</TITLE>
<ARTIST>Bob Dylan</ARTIST>
<COUNTRY>USA</COUNTRY>
<COMPANY>Columbia</COMPANY>
<PRICE>10.90</PRICE>
<YEAR>1985</YEAR>
</CD>
<CD>
<TITLE>Hide your heart</TITLE>
<ARTIST>Bonnie Tyler</ARTIST>
<COUNTRY>UK</COUNTRY>
<COMPANY>CBS Records</COMPANY>
<PRICE>9.90</PRICE>
<YEAR>1988</YEAR>
</CD>
.
.
.
</CATALOG>
```

使用 CSS 格式化 XML 不是常用的方法。W3C 推荐使用 XSLT


# SE Team 19 - Iteration 4

## Team

**Team (mit Github Name):**
- Isabella Habereder (IsaHab)
- Daqian Zhang (Zhang-Daqian)
- Jan Pfeifer (pfeifer-j)
- Quirin Haberl (QuirinHaberl)
- Florian Muhrer (FlorianMuhrer)


**Scrum Master für Iteration 4:**
1. Florian Muhrer
2. Quirin Haberl
3. Isabella Habereder
> 4. Jan Pfeifer
5. Daqian Zhang


**Organisation:**
- Mehrmalige, wöchentliche Absprache über Zoom und WhatsApp.
- Fortschritte, Probleme und Fragen wurden in der Gruppe besprochen. 


**Agenda für Iteration 4:**
- Umsetzung der grafischen Oberfläche:
	- Erstellung der Projektstruktur des GUI's durch Florian mit Hilfe des Betreuers Georg.
	- Design der graphischen Oberfläche (v. a. Fenster, Icons) wurde hauptsächlich von Florian übernommen.
	- Implementierung der grundlegenden Steuerung des Memoriespiels durch Quirin, Isabella und Florian.
  
- Implementierung der noch fehlenden, geplanten Features:
 	- SinglePlayer-Spielmodi 'Spiel gegen die Zeit' und 'Spiel mit Leben' durch Isabella.
	- HighScore- und Achievementtracking durch Jan und Quirin.
	- Fortschrittsspeicherung in einem Spielerprofil bzw. in der HighScoreHistory durch Jan, Daqian und Quirin.
  
- Refactoring und Dokumentaion des Codes:
	- Steigerung der Lesbarkeit durch Namensänderungen, Auslagerung im Methoden und Optimierung einiger Codepassagen durch das ganze Team.
	- Dokumentaion via JavaDoc angepasst bzw. neu hinzugefügt. Jedes Teammitglied hat hier beigetragen.
  
- Verwendung der Entwurfsmuster/Entwurfsprinzipien:
	- Umsetzung des Singleton Patterns in den Klassen Controller, Game, Database, HighScoreHistory durch Quirin und Isabella. Da es hier zu Fehlern nach der Implementierung 	   der Pattern kam, wurden Controller und Game wieder in den ursprünglichen Zustand versetzt.
	- die Umsetzung des Observer Patterns in den Klassen DataDisplay durch Isabella war geplant und wird in der nächsten Iteration erfolgen.
	- im Zuge der nächsten Iteration wird beim Refactoring darauf geachted das Gesetz von Demeter zu berücksichtigen.
  
- Abnahme des Projekts am 04.07.21:
	- Nach Iteration 5 ist die Entwicklung der GUI weit fortgeschritten, jedoch noch nicht abgeschlossen. Die spielbare Version wird noch auf der Konsole ausgegeben.
	- Das Klassendiagramm wurde in der Gruppe umgesetzt und von Jan generiert.
  
- Iteration 5 planen:  
	- User Stories auswählen und ggf. anpassen und neu erstellen.
	- Tasks zum Refactoring, zur Dokumenation und zu den Tests erstellen durch Jan.
	- Kanbanboard und Milestone der 5. Iteration erstellen durch Jan.



**Anhang:**  
Protokolle der Zoom-Meetings:
- Protokoll_23_06_21.txt
- Protokoll_27_06_21.txt
- Protokoll_30_06_21.txt
- Protokoll_04_07_21.txt


**Klassendiagramm:**  
Das Klassendiagramm wurde im Team aktualisiert und von Jan generiert. 
Da das GUI noch nicht lauffähig ist und noch Änderungen an der Programmstruktur geplant sind,
ist das Klassendiagram sehr unübersichtlich. Dies wird sich nach dem Refactoring verbessern.


**Iteration 4**

#User Stories

**E = Erstellt**
**B = Beigetragen**

| ID | Isabella Habereder | Daqian Zhang | Jan Pfeifer | Quirin Haberl | Florian Muhrer|
|:---:|:------------:|:------------:|:------------:|:------------:|:------------:|
| #8 |E|||B||
| #17 |E|||B|
| #18 |E|||B|B|
| #20 |E|||||
| #27 ||E||||
| #33 |||E|||
| #35 |||E|B||
| #37 ||||E||
| #38 ||||E||
| #125 |E|||||
| #156 |||E|||


#Tasks

**P = Planung**
**R = Realisierung**
**C = Closed**

| ID | Isabella Habereder | Daqian Zhang | Jan Pfeifer | Quirin Haberl | Florian Muhrer|
|:---:|:------------:|:------------:|:------------:|:------------:|:------------:|
| #95  |||||P, R|
| #126 |P, R|||||
| #127 |P, R|||||
| #129 |||R|R|P|
| #130 ||P||C||
| #131 |R|P|C|||
| #132 ||P, R|R|||
| #135 ||R|P, R|||
| #136 |||P, R|||
| #137 |||P, R|||
| #143 |||P, R|R||
| #150 ||||P, R||
| #157 |||P, R|||
| #167 |R|R|P, R|R|R|
| #168 |P, R|||||
| #169 |P, R|||||
| #172 |P, R|||||
| #182 |P, R|||||


**Planung Iteration 5**

Die Auswahl der User Stories für Iteration 5 ist zum Teil aus existieren User Storys der vorherigen Iterationen erfolgt.
Für die JavaDoc-Dokumentation sowie für das Refactoring wurden keine User Stories erstellt.

## Tasks und User Stories

**E = Erstellt**
**B = Beigetragen**

| ID | Isabella Habereder | Daqian Zhang | Jan Pfeifer | Quirin Haberl | Florian Muhrer|
|:---:|:------------:|:------------:|:------------:|:------------:|:------------:|
| #166 |||E|||
| #167 |||E|||
| #168 |||E|||
| #174 |||E|||
| #175 |||E|B||
| #176 |B||E|||
| #177 ||B|E|||
| #178 |||E||B|
| #179 |||E|||
| #180 |||E|||

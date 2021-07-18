# SE Team 19 - Iteration 5

## Team

**Team (mit Github Name):**
- Isabella Habereder (IsaHab)
- Daqian Zhang (Zhang-Daqian)
- Jan Pfeifer (pfeifer-j)
- Quirin Haberl (QuirinHaberl)
- Florian Muhrer (FlorianMuhrer)


**Scrum Master für Iteration 5:**
1. Florian Muhrer
2. Quirin Haberl
3. Isabella Habereder
4. Jan Pfeifer
> 5. Daqian Zhang


**Organisation:**
- Mehrmalige, wöchentliche Absprache über Zoom und WhatsApp.
- Fortschritte, Probleme und Fragen wurden in der Gruppe besprochen.
- Die Projektpräsentation für den 15.07.2021 vorbereiten und halten.

**Agenda für Iteration 5:**
- Umsetzung des GUI:
    - Das Spiel wurde spielbar gemacht. Sprich, Kisten werden nach dem Öffnen ausgeblendet und Punkte der Spieler werden aktualisiert. Hier hat das ganze Team geholfen.
    - Die verschiedenen Spielmodi sind nun spielbar. Die Implementierung Modus auf Leben übernahm Quirin - den Modus gegen die Zeit Isabella
    - Das Spielergebnis erscheint in einem separaten Fenster, nachdem ein Spiel beendet wurde. Hier wird zwischen Sieg, Niederlage und Unentschieden unterschieden. Dies wurde         hauptsächlich von Florian und Quirin umgesetzt.
    - Eine zum jeweilig auftretenden Event abgestimmte Geräuschkullisse ist implementiert worden. Hier übernahm Florian die Beschaffung der Sounddateinen.
    - Die Achievements der Spieler werden im laufenden Spiel angezeigt werden. Jan war dafür zuständig.
    - Dynamische Text Anpassung der Achievements wurde durch Florian realisiert.
    - Die Funktionen rund ums Spielmenü sind nun im laufenden Spiel verwendbar. Isabella und Quirin haben diese Fuktionen implementiert.
    - Die Spielerfprofile im GUI wurden von Jan implementiert.
    - Die HighScoreHistory im GUI wurde von Quriin implementiert.
    
- Refactoring:
    - Der bestehende Code aller Klassen des Projekts wurden vom gesamten Team gerefactored.
    - Die Logik der Methoden wurde vereinfachet.
    - Ungenutze Methoden wurden entfernt.
    - Die Projektstruktur wurde angepasst.
    
- JUnit Tests:
    - Die Tests von Player, PlayerList, PlayingField und Card wurden von Daqian geschrieben.
    - Die Tests von Game wurden von Isabella geschrieben.
    - Die Tests von DataBase, Achievement und den Controllern wurden von Jan geschrieben.
   
- Dokumentation mit JavaCode:
    - Das ganze Team hat fehle Dokumentation ergänzt und bestehende verbessert.

**Anhang:**  
Protokolle der Zoom-Meetings:
- Protokoll_07_07_21.txt
- Protokoll_11_07_21.txt
- Protokoll_18_07_21.txt

**Klassendiagramm:**  
Das Klassendiagramm wurde im Team aktualisiert und von Quirin generiert. 

**Iteration 5**

#User Stories

**E = Erstellt**
**B = Beigetragen**

| ID | Isabella Habereder | Daqian Zhang | Jan Pfeifer | Quirin Haberl | Florian Muhrer|
|:---:|:------------:|:------------:|:------------:|:------------:|:------------:|
| #17 |E|||B|
| #33 ||B|E|B||
| #180 |B|B|E|B|B|
| #20 |E|||||


#Tasks

**P = Planung**
**R = Realisierung**
**C = Closed**

| ID | Isabella Habereder | Daqian Zhang | Jan Pfeifer | Quirin Haberl | Florian Muhrer|
|:---:|:------------:|:------------:|:------------:|:------------:|:------------:|
| #166 |R|R|P,R|R|R|
| #167 |R|R|P,R|R|R|
| #168 |R|R|P,R|R|R|
| #174 |||P,R|||
| #175 ||R|P|||
| #176 |R||P|||
| #177 ||R|P|||
| #178 |||P||R|
| #178 |||P,R|R||
| #184 |||||P,R|
| #186 |||P,R|||
| #187 |||P,R|R||
| #186 |||P,R|||
| #190 |||P,R||R|
| #191 |||P,R|||
| #194 |||P,R|||
| #195 |||P,R|R||
| #196 |||P,R|||
| #197 |||P,R|R|R|
| #197 |R||P,R|R|R|
| #199 |R||P,R|||
| #201 |||P,R|R|R|
| #202 |||P,R|||
| #205 |||P,R||R|
| #212 |P,R|||||
| #213 |P,R|||||
| #216 ||||P,R||
| #218 ||||P,R||
| #219 |R|R|R|P,R|R|
| #220 ||||P,R||
| #226 |||P,R|||
| #236 |||||P,R|
| #237 |||||P,R|
| #238 |||||P,R|
| #259 |||P,R|||
| #260 |||P,R|||
| #263 |||R||P,R|

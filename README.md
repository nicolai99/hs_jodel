# Jodel by Linus Macha & Nicolai Glock

## Idee

Es gibt Posts (Jodel), diese können:

- erstellt werden
- kommentiert werden
- up and down gevoted also 1 oder -1, der jeweilige Vote muss ersetzt werden !
- haben einen Ersteller und ein Erstellzeitpunkt
- Sortierung nach:
  - Erstellzeitpunkt
  - Anzahl Kommentare
  - Lautestes Jodel (Anzahl der Votes)
- Eingeschränkt durch Radius 10 km

Es gibt unbegrenzt viele Kommentare zu jedem Jodel, diese können:

- erstellt werden
- up and down gevoted also 1 oder -1, der jeweilige Vote muss ersetzt werden !
- haben einen Ersteller und ein Erstellzeitpunkt

Link zum Prototyp der App (mit Figma):

- https://www.figma.com/design/y7ymbjnrYof53G8lXBUIFR/SWA---Jodel?node-id=0-1&t=pY7bDe0HZ3BGvaWF-1

## Hinweise

### Starten im dev mode

im Verzeichnis /backend/jodel \
mvn spring-boot:run -Dspring-boot.run.profiles=dev

## Openstreet API

https://nominatim.openstreetmap.org/reverse?lat=48.738406&lon=9.30811

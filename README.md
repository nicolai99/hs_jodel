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

## Hinweise

### Starten im dev mode

mvn spring-boot:run -Dspring-boot.run.profiles=dev

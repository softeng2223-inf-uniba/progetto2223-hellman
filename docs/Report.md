# Report
**Indice**
 + [Introduzione](#1-introduzione)
 + [Modello di dominio](#2-modello-di-dominio)
 + [Requisiti specifici](#3-requisiti-specifici)
	 + [Requisiti funzionali](#31-requisiti-funzionali)
	 + [Requisiti non funzionali](#32-requisiti-non-funzionali)
+ [Manuale utente](#7-manuale-utente)
+ [Analisi retrospettiva](#9-analisi-retrospettiva)
	 + [Sprint 0](#91-sprint-0)

# 1. Introduzione
Benvenuti a "Battaglia Navale", classico gioco da tavolo in cui sfiderai un avversario virtuale controllato dal computer. 
Preparati a mettere alla prova le tue abilità strategiche in un emozionante scontro navale.
"Battaglia Navale" offre un'esperienza coinvolgente e realistica, consentendoti di giocare contro un avversario che userà strategie avanzate per cercare di distruggere le tue navi. 
Potrai scegliere diversi livelli di difficoltà, da facile a difficile, per adattare la sfida alle tue capacità.
All'inizio del gioco, avrai la possibilità di posizionare le tue navi sulla tua griglia. 
Dovrai scegliere attentamente le posizioni delle tue navi per nasconderle al meglio e rendere difficile individuarle. 
Assicurati di utilizzare strategie di posizionamento tattiche per mettere in difficoltà il tuo avversario.
Una volta che le navi sono posizionate, inizia la fase di attacco. 
La CPU dispone le navi che tu giocatore dovrai affondare, sparando missili sulla griglia. 
	
	
# 2. Modello di dominio

# 3. Requisiti specifici

## 3.1 Requisiti funzionali

## 3.2 Requisiti non funzionali

# 7. Manuale utente
Schermata Principale:
Quando verrà avviata l'app, si verrà accolti dalla schermata principale. Da qui, si avrà accesso alle seguenti opzioni per selezionare il livello di difficoltà della partita:

- facile: digitare '/facile';
- medio: digitare '/medio';
- difficile: digitare '/difficile'.

Se si desidera visualizzare il livello di difficoltà selezionato, digitare nella linea di comando:
- '/mostralivello'.

Se si desidera visualizzare le navi (pedine di gioco) a disposizione, digitare nella linea di comando:
- '/mostranavi'.

Per avviare una partita, una volta impostato il livello di difficoltà, digitare '/gioca' e attendere.
Verranno generate casualmente la posizione e l'orientamento delle navi e verrà  verificato che non ci siano sovrapposizioni.
Se non vengono rilevati errori, verrà visualizzato il messaggio 'Navi posizionate e partita iniziata.', in caso contrario 
sarà necessario riavviare l'esecuzione del gioco.
Adesso sarà possibile visualizzare la griglia di gioco con la disposizione delle navi.

# 9. Analisi retrospettiva

## 9.1 Sprint 0

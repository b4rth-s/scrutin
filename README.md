<h1>Ballots simulation</h1>
<p>This program, realised during an IT course at Mines ParisTech, aims to simulate an election. The final goal is to evaluate differents types of election (election by majority with 1 or 2 round(s), election with elimination, etc.). It works as follows.</p>
<p>Before the election, we consider that we have a "situation". A "situation" is basically a society with people voters and candidate. We consider that every voter has, at least in his head, a very clear ranking of the candidates. That is very important because they will therefore be able to make a decision in front of the ballots.</p>
<p>This "situation" is represented in a .stn file. In these file, there are the number of voters, the number of candidates, their names and the rankings of every voter. This file is readable by humans so that they can modify manually or even create situations.</p>
<p>An election is also made by its method of election. We call that with the French word "scrutin". In this program, we have developed 3 "scrutins": election by majority with 1 round, election by majority with 2 rounds and election by elimination.</p>
<p>In order to evaluate a "scrutin" (which is the goal of this program), we need to define a way of ranking them. We do that by defining a "satisfaction function". The first one we have programmed is an average of the gap.</p>
<p>After the election, there are results, represented in the .rslt files. These files sum up the caracteristics of the situation, the "scrutin" and the satisfaction function. It then give the results in various format types depending on the "scrutin". Many results for different "scrutins" with the same situation can be on the same .rslt file.</p>
<div align="center">
	<img src="Captures/Capture 1.png" alt="Capture1"
	title="Capture1" width="400" />
	<img src="Captures/Capture 2.png" alt="Capture2"
	title="Capture2" width="400" />
</div>
<div align="center">
	<img src="Captures/Capture 3.png" alt="Capture3"
	title="Capture3" width="400" />
	<img src="Captures/Capture 4.png" alt="Capture4"
	title="Capture4" width="400" />
</div>

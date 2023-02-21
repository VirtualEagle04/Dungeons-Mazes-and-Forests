# DMF: Dungeon, Mazes and Forests

Project objetives were:
- Create a maze-like game, where user can enter X . Y dimensions of the maze.
- The maze has AT LEAST one solution.
- The player has a maximum of movements which is equal to the product of the maze's dimentions.
- To complete the maze, the player needs the "Keys" scattered around the maze. 
- There are two enemy types in the maze: Lethal and Stormy. Minimun one of each is required (Player chooses how many in maze creation screen):
      -> When encountered with the Lethal enemy (The position next to the player in all directions), instant loss.
      -> When encountered with Stormy enemy, max movements - (coord.x + coord.y)
- Both enemy types can move one square each time Player moves (which also moves one square)

Extra requirements (Proposed by team members): 
- Music and SFX
- RPG-style Character class selection (purely aesthetic).
- Sprites and Animations

## No Minimap, Sorry

On join and respawn, the player has their Minimap (and Worldmap) as well as their waypoints disabled.

Players with the `nominimap.bypass` will be immune to this plugins effects. If the player gets this permission whilst connected to the server, they will need to be respawned.

It depends on the unreleased PacketEvents 2.0, so if you need it you should open a GitHub issue. Hopefully by the time anyone else needs this PacketEvents 2.0 is finished!

### Proof
It's a little ugly to the client, but it works! 
![](https://cdn.discordapp.com/attachments/1071578610860822539/1134403267909136394/image.png)

The potion effect is *purely* cosmetic, as a result a few quirks apply:
* The player cannot drink milk to clear the potion effect
* The player cannot have their potion effects cleared with `/effect clear`
* The player may not be able to drink potions? (untested)
* the potion effects will have a `00:00` duration. 

### About
This plugin was made for my friends at OfflineTV 😄

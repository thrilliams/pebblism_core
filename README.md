# Pebblism Core

This mod was written to improve compatability for the Pebblism 3 modpack, and does the following:

- Include the functionality of Capacity in the Amphibious enchantment (but not remove Capacity from the allowed enchantment pool, you need to do that yourself \[just add `create:capacity` to `disallowedEnchantments` in config/enchancement.json])
- Maintain fire resistance from Create's Netherite diving gear when any of it is replaced with Warden armor from Deeper and Darker
- Allow Riptide Tridents to be used regardless of conditions when the player equips a Totem of Torrents from Better Archeology in their Charm slot
- Remove tutorial toasts
- Add a gamerule that disables mob explosion block damage (`doMobExplosionBlockDamage`)
- Add an option to stop Transmog from modifying items other than equipped armor
- Fix Enchancement not recognizing the Perception enchantment on Transmogged helmets
- Fix a bug with Create rendering Sequenced Assemblies that contain more than one fluid ingredient in REI (just until that fix gets into a release)
- Fix a crash with Create: Estrogen on MacOS (just until that fix gets into a release)

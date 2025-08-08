package bravelybeep.ourcraftfixes.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.mob.AbstractSkeletonEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.World;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(AbstractSkeletonEntity.class)
public class AbstractSkeletonEntityMixin extends HostileEntity {
	protected AbstractSkeletonEntityMixin(EntityType<? extends HostileEntity> entityType, World world) {
		super(entityType, world);
	}

	/**
	 * @author bravely_beep
	 * @reason Only set held item to bow if there is no existing held item.
	 */
	@Overwrite
	public void initEquipment(Random random, LocalDifficulty localDifficulty) {
		super.initEquipment(random, localDifficulty);
		if (this.getEquippedStack(EquipmentSlot.MAINHAND).getItem() == Items.AIR) {
			this.equipStack(EquipmentSlot.MAINHAND, new ItemStack(Items.BOW));
		}
	}
}

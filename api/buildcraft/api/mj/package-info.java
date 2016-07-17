/** Contains the Minecraft Joule API (shortened to Mj), reborn.
 * <p>
 * MJ is stored in the long type, as micro Mj - so you need a long storing a value of 1 million to have a single MJ. All
 * power should be passed around in code as micro Mj (µMj), but shown to the player as full MJ - so divided by 1
 * million.
 * <p>
 * A single MJ as a constant is available in {@link buildcraft.api.mj.MjAPI#ONE_MINECRAFT_JOULE}, and two player
 * formatter's ( {@link buildcraft.api.mj.MjAPI#formatMj(long)}, and {@link buildcraft.api.mj.MjAPI#formatMjShort(long)}
 * <p>
 * <h1>Capability's</h1>
 * <p>
 * MJ should be exposed from tile entities and entities via the forge capability system as one of the following types:
 * <ul>
 * <li>{@link buildcraft.api.mj.IMjConnector} for *anything* that can (potentially) connect to any other Mj type. All
 * other types extend this. You should expose this with the capability
 * {@link buildcraft.api.mj.MjAPI#CAP_CONNECTOR}</li>
 * <li>{@link buildcraft.api.mj.IMjReadable} for something that has an internal battery. This is used for adding gate
 * triggers. This should be exposed with the capability {@link buildcraft.api.mj.MjAPI#CAP_READABLE}</li>
 * <li>{@link buildcraft.api.mj.IMjReceiver} for something that receives power. This should be exposed with the
 * capability {@link buildcraft.api.mj.MjAPI#CAP_RECEIVER}</li>
 * </ul>
 * Note that you *must* expose the base interface types as capabilities as well as the top type so if you have an
 * instance of {@link buildcraft.api.mj.IMjReceiver} then you must return it for both of the capability's
 * {@link buildcraft.api.mj.MjAPI#CAP_CONNECTOR} and {@link buildcraft.api.mj.MjAPI#CAP_RECEIVER}. A simple way to do
 * this is provided in {@link buildcraft.api.mj.MjCapabilityHelper}.
 * <p>
 * <h1>Mj Type</h1>
 * <p>
 * Machines should be exposed with an instance of {@link buildcraft.api.mj.IMjConnectorType}, the default of which are
 * provided in {@link buildcraft.api.mj.MjSimpleType}. Note that it IS an interface, so you can make your own classes or
 * enum types to properly represent the machines in your mod - provided that you return the simple type for all of
 * them. All BuildCraft machine types are provided in {@link buildcraft.api.mj.types}*/
package buildcraft.api.mj;

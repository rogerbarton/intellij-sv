package ch.rbarton.intellijsv.ide

import com.intellij.icons.AllIcons
import com.intellij.openapi.util.IconLoader

object SvIcons
{
    @JvmField
    val SV_FILE = IconLoader.getIcon("/icons/svFile.svg", SvIcons.javaClass)

    @JvmField
    val SV_MODULE = AllIcons.General.Layout

    @JvmField
    val SV_MODULE_INSTANCE = AllIcons.Actions.GroupByModule

    @JvmField
    val SV_NET = IconLoader.getIcon("/icons/svNet.svg", SvIcons.javaClass)

    @JvmField
    val SV_PORT_INPUT = IconLoader.getIcon("/icons/portInput.svg", SvIcons.javaClass)

    @JvmField
    val SV_PORT_OUTPUT = IconLoader.getIcon("/icons/portOutput.svg", SvIcons.javaClass)

    @JvmField
    val SV_PORT_INOUT = IconLoader.getIcon("/icons/portInout.svg", SvIcons.javaClass)

    @JvmField
    val SV_PORT_REF = IconLoader.getIcon("/icons/portRef.svg", SvIcons.javaClass)

    @JvmField
    val SV_PARAM = AllIcons.Nodes.Parameter

    @JvmField
    val SV_LOCALPARAM = AllIcons.Nodes.Property

    @JvmField
    val SV_TIME = IconLoader.getIcon("/icons/time.svg", SvIcons.javaClass)

    @JvmField
    val SV_CONTINUOUS_ASSIGN = AllIcons.Vcs.Equal

    @JvmField
    val SV_INITIAL = AllIcons.Nodes.Interface

    @JvmField
    val SV_FINAL = AllIcons.Nodes.Interface

    @JvmField
    val SV_GENERATE = AllIcons.Actions.GroupBy

    @JvmField
    val SV_TYPE = AllIcons.Nodes.Type

    @JvmField
    val SV_ALWAYS_FF = AllIcons.Nodes.Annotationtype

    @JvmField
    val SV_ALWAYS_COMB = AllIcons.Nodes.Interface

    @JvmField
    val SV_ALWAYS_LATCH = AllIcons.Nodes.Interface

    @JvmField
    val SV_ALWAYS = AllIcons.Nodes.Interface

    @JvmField
    val SV_MODULE_ITEM = AllIcons.Nodes.Interface
}
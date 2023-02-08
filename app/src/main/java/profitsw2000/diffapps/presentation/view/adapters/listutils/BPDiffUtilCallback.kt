package profitsw2000.diffapps.presentation.view.adapters.listutils

import androidx.recyclerview.widget.DiffUtil
import profitsw2000.diffapps.model.BloodPressure

class BPDiffUtilCallback(
    private val oldList: List<BloodPressure>,
    private val newList: List<BloodPressure>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldBloodPressure = oldList[oldItemPosition]
        val newBloodPressure = newList[newItemPosition]
        return oldBloodPressure.id == newBloodPressure.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldBloodPressure = oldList[oldItemPosition]
        val newBloodPressure = newList[newItemPosition]
        return oldBloodPressure.date == newBloodPressure.date &&
                oldBloodPressure.highPressure == newBloodPressure.highPressure &&
                oldBloodPressure.lowPressure == newBloodPressure.highPressure &&
                oldBloodPressure.pulse == newBloodPressure.pulse
    }
}
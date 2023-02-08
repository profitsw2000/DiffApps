package profitsw2000.diffapps.presentation.view.adapters.listutils

import androidx.recyclerview.widget.DiffUtil
import profitsw2000.diffapps.model.DayBloodPressure

class DayBPDiffUtilCallback(
    private val oldList: List<DayBloodPressure>,
    private val newList: List<DayBloodPressure>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldDayBloodPressure = oldList[oldItemPosition]
        val newDayBloodPressure = newList[newItemPosition]
        return oldDayBloodPressure.id == newDayBloodPressure.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldDayBloodPressure = oldList[oldItemPosition]
        val newDayBloodPressure = newList[newItemPosition]
        return oldDayBloodPressure.date == newDayBloodPressure.date &&
                oldDayBloodPressure.bloodPressureList.equals(newDayBloodPressure.bloodPressureList)
    }
}
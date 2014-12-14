package com.com.activity.personal;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.com.R;
import com.com.service.UserService.Orders;

	public class OrdersAdapter extends BaseAdapter {
		private Context context;
		private List<Orders> orders;

		public OrdersAdapter(Context context,List<Orders> orders) {
			this.context = context;
			this.orders = orders;
		}

		@Override
		public int getCount() {
			return orders.size();
		}

		@Override
		public Object getItem(int position) {
			return orders.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = LayoutInflater.from(context).inflate(
						R.layout.order_item, null);
			}
			final Orders orders2 = orders.get(position);
			TextView orders_id = (TextView) convertView.findViewById(R.id.orders_id);
			orders_id.setText(String.valueOf(orders2.getOrders_id()));

			TextView goods_item_count = (TextView) convertView.findViewById(R.id.goods_item_count);
			goods_item_count.setText(String.valueOf(orders2.getOrders_goods_item_count()));

			TextView goods_amount = (TextView) convertView.findViewById(R.id.goods_amount);
			goods_amount.setText(String.valueOf(orders2.getOrders_goods_amount()));

			TextView mail_charg = (TextView) convertView.findViewById(R.id.mail_charg);
			mail_charg.setText(String.valueOf(orders2.getOrders_mail_charge()));

			TextView status = (TextView) convertView.findViewById(R.id.status);
			status.setText(orders2.getOrders_status());
			
			TextView pay_mode = (TextView) convertView.findViewById(R.id.pay_mode);
			pay_mode.setText(orders2.getOrders_pay_mode());
			
			TextView mail_mode = (TextView) convertView.findViewById(R.id.mail_mode);
			mail_mode.setText(orders2.getOrders_mail_mode());
			
			TextView date = (TextView) convertView.findViewById(R.id.date);
			date.setText(orders2.getOrders_date());
			return convertView;
		}
	}
import ButtonPrimary from "../components/buttons/ButtonPrimary";

export default function SignUp() {
	return (
		<div className='grid grid-cols-5 min-h-screen min-w-screen'>
			<div className='col-span-3'>
				<img
					className='w-full h-full object-cover'
					src='https://images.unsplash.com/photo-1570347929626-2bbc8032d98b?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1471&q=80'
					alt='Gato em uma janela'
				/>
			</div>
			<div className='col-span-2 bg-vetConnectWhite'>
				<form action='' method='post'>
					<div className='p-10 flex flex-col gap-5'>
						<div>
							<label
								htmlFor='email'
								className='block text-sm font-medium leading-6 text-gray-900'
							>
								Email address
							</label>
							<div className='mt-2'>
								<input
									id='email'
									name='email'
									type='email'
									autoComplete='email'
									required
									className='block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6'
								/>
							</div>
						</div>
						<ButtonPrimary text={"Cadastrar"} />
					</div>
				</form>
			</div>
		</div>
	);
}
